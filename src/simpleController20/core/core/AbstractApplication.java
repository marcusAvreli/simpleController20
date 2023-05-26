package simpleController20.core.core;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleController20.api.controller.ViewControllerDispatcher;
import simpleController20.api.core.Application;
import simpleController20.api.core.ApplicationContext;
import simpleController20.api.core.ApplicationContextException;
import simpleController20.api.core.ApplicationEvent;
import simpleController20.api.core.ApplicationListener;
import simpleController20.api.view.ViewContainer;
import simpleController20.api.view.ViewContainerFrame;
import simpleController20.api.view.ViewException;
import simpleController20.api.view.ViewManager;
import simpleController20.api.view.ViewManagerException;
import simpleController20.api.view.perspective.PerspectiveConstraint;
import simpleController20.core.annotation.processor.ViewsProcessorWrapper;
import simpleController20.core.controller.DefaultViewControllerDispatcher;
import simpleController20.core.view.DefaultViewManager;
import simpleController20.core.view.event.DefaultViewContainerEventController;
import simpleController20.core.view.perspective.DefaultPerspective;
import simpleController20.core.view.perspective.MyPerspective;
import simpleController20.widget.swing.builder.util.SwingBuilderView;
import simpleController20.widget.swing.builder.util.CertificationView;
import simpleController20.widget.swing.builder.util.CustomApplicationView;


/**
 * This is a default implementation of an Application which has
 * a <code>ViewControllerDispatcher</code> as well as a
 * <code>ViewManager</code>.
 * 
 * @author Mario Garcia
 * @since 1.0
 *
 */

//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/core/src/main/java/org/viewaframework/core/AbstractApplication.java
/**
 * This is a default implementation of an Application which has
 * a <code>ViewControllerDispatcher</code> as well as a
 * <code>ViewManager</code>.
 * 
 * @author Mario Garcia
 * @since 1.0
 *
 */

//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/core/src/main/java/org/viewaframework/core/AbstractApplication.java
public abstract class AbstractApplication implements Application
{
	private static final Logger logger = LoggerFactory.getLogger(AbstractApplication.class);
	private ApplicationContext			applicationContext;
	private List<ApplicationListener>	applicationListeners;
	private Locale						locale;
	private ViewControllerDispatcher 	dispatcher;
	private String 						name;
	private ViewContainerFrame			rootView;
	private ViewManager 				viewManager;
	private List<ViewsProcessorWrapper> initViews;
	public AbstractApplication(){
		logger.info("constructor_0");
		//this.viewManager 			= new DefaultViewManager(this,new DefaultPerspective());
		this.viewManager 			= new DefaultViewManager(this,new MyPerspective());
		this.dispatcher 			= new DefaultViewControllerDispatcher();
		this.applicationListeners 	= new ArrayList<ApplicationListener>();
		
		ViewContainer vc = new SwingBuilderView(); 
		
		ViewContainer tableContainer = new CustomApplicationView();
	
		//public ViewsProcessorWrapper(ViewContainer view,PerspectiveConstraint constraint,boolean rootView,boolean trayView){
		ViewsProcessorWrapper wrapper = new ViewsProcessorWrapper(vc, PerspectiveConstraint.LEFT, false, false);
		initViews = new ArrayList<ViewsProcessorWrapper>();
		initViews.add(wrapper);
		wrapper = new ViewsProcessorWrapper(tableContainer, PerspectiveConstraint.RIGHT, false, false);
		//tableContainer.addViewContainerListener(new DefaultViewContainerEventController());
		initViews.add(wrapper);
	//	wrapper = new ViewsProcessorWrapper(tabContainer, PerspectiveConstraint.RIGHT, false, false);
		//initViews.add(wrapper);
	}
	
	/**
	 * @param name
	 * @param mainView
	 */
	public AbstractApplication(String name,ViewContainerFrame mainView){
		logger.info("constructor_1");
		this.setName(name);
		this.setRootView(mainView);
	}
	/**
	 * This constructor sets the default root view
	 * 
	 * @param mainView
	 */
	public AbstractApplication(ViewContainerFrame mainView){
		logger.info("constructor_2");
		this.viewManager 			= new DefaultViewManager(this,new DefaultPerspective());
		this.setRootView(mainView);
	}
	

	/* (non-Javadoc)
	 * @see org.viewaframework.core.Application#close()
	 */
	public void close() {
		
		logger.info("Application_closing!");
		this.fireClose(new ApplicationEvent());
	}
	public void fireClose(ApplicationEvent event) {
		
		
		 /* First we execute all related listeners */
		for (ApplicationListener applicationListener : this.applicationListeners){
			applicationListener.onClose(event);
			/* Some kind of vetoableCloseException should being created. If any view
			 * throws an exception like that closing process should be aborted otherwise
			 * the application finishes even if there's other type of exceptions.*/
		}
	 /* Finally we close all children views */
		Collection<ViewContainer> views = this.getViewManager().getViews().values();
	 /* Except the root view */
		ViewContainerFrame viewContainerFrame = this.getViewManager().getRootView();
	 /* Closing all children views */
		for (ViewContainer view:views){
			if (view != viewContainerFrame)
				try {
					/* This should be view.viewSave(); or persist just the view collection from the
					 * application. Next time application starts the collection will be retrieved
					 * again and the collections could be added to the application. */
					view.viewClose();
				} catch (ViewException e) {
					throw new RuntimeException(e);
				}
		}		
	 /* And finally we close the root view */
		viewContainerFrame.getFrame().dispose();
		}
	/* (non-Javadoc)
	 * @see org.viewaframework.core.ApplicationListenerAware#fireClose(org.viewaframework.core.ApplicationEvent)
	 */
	
	@SuppressWarnings("unchecked")
	public void prepare(){
		logger.info("Application preparing!");
		if (this.applicationContext == null){
			this.applicationContext = new DefaultApplicationContext();
		}
		
	}
	public ViewContainerFrame getRootView() {
		return rootView;
	}
		/* (non-Javadoc)
		 * @see org.viewa.core.ApplicationBase#getName()
		 */
		public String getName() {
			return this.name;
		}
	/* (non-Javadoc)
	 * @see org.viewaframework.core.Application#prepareUI()
	 */
	public void prepareUI() {
		logger.info("Application preparing UI!");
		
		for (ViewsProcessorWrapper w : initViews){
			try {
				
				if (w.isRootView()){
					this.setRootView(ViewContainerFrame.class.cast(w.getView()));
				} else {
					logger.info("=========================");
					this.getViewManager().addView(w.getView(),w.getConstraint());
				
				}
			} catch (ViewException e) {
				logger.error("ssss=============");
			}
		}
		
	}


	public void initUI() {
		
			logger.info("Application UI initializing!");
			ViewManager viewManager = this.getViewManager();
	
			Component 	view 		= viewManager.arrangeViews();
	
			view.setVisible(true);
	
		
	}
	public ViewManager getViewManager() {
		return viewManager;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
		Locale.setDefault(this.locale);
	}

	/* (non-Javadoc)
	 * @see org.viewa.core.ApplicationBase#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param rootView
	 */
	public void setRootView(ViewContainerFrame rootView) {
				
		
			if (this.viewManager!=null){
				this.viewManager.setRootView(rootView);
				this.rootView = rootView;
			}else {
				logger.info("viewManager is null");
			}
		
		
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.core.Application#isVisible()
	 */
	public boolean isVisible(){
		
		boolean result = true;
		return result;
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.core.Application#setVisible(boolean)
	 */
	public void setVisible(boolean visible){
		JFrame frame = this.getRootView().getFrame();
		if (frame != null){
			if (visible){
				frame.setVisible(visible);
				frame.repaint();
			} else {
				frame.setVisible(visible);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.viewa.core.Application#setViewManager(org.viewa.view.ViewManager)
	 */
	public void setViewManager(ViewManager viewManager) throws ViewManagerException {
		this.viewManager = viewManager;
	}
	/* (non-Javadoc)
	 * @see org.viewa.core.ApplicationBase#getControllerDispatcher()
	 */
	public ViewControllerDispatcher getControllerDispatcher() {
		return this.dispatcher;
	}
	public void setControllerDispatcher(ViewControllerDispatcher dispatcher)  {
		this.dispatcher = dispatcher;
	}
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext)  throws ApplicationContextException {
		if (this.applicationContext != null) {
			throw new ApplicationContextException();
		}
		this.applicationContext = applicationContext;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.core.ApplicationListenerAware#addApplicationListener(org.viewaframework.core.ApplicationListener)
	 */
	public void addApplicationListener(ApplicationListener listener) {
		this.applicationListeners.add(listener);
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.core.ApplicationListenerAware#fireinitUI(org.viewaframework.core.ApplicationEvent)
	 */
	public void fireinitUI(ApplicationEvent event) {
		for (ApplicationListener l : this.applicationListeners){
			l.onInitUI(event);
		}
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.core.ApplicationListenerAware#firePrepare(org.viewaframework.core.ApplicationEvent)
	 */
	public void firePrepare(ApplicationEvent event) {
		for (ApplicationListener l : this.applicationListeners){
			l.onPrepare(event);
		}
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.core.ApplicationListenerAware#firePrepareUI(org.viewaframework.core.ApplicationEvent)
	 */
	public void firePrepareUI(ApplicationEvent event) {
		for (ApplicationListener l : this.applicationListeners){
			l.onPrepareUI(event);
		}
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.core.ApplicationListenerAware#getApplicationListeners()
	 */
	public List<ApplicationListener> getApplicationListeners() {
		return applicationListeners;
	}
	/* (non-Javadoc)
	 * @see org.viewaframework.core.ApplicationListenerAware#removeApplicationListener(org.viewaframework.core.ApplicationListener)
	 */
	public void removeApplicationListener(ApplicationListener listener) {
		this.applicationListeners.remove(listener);
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.core.ApplicationListenerAware#setApplicationListeners(java.util.List)
	 */
	public void setApplicationListeners(List<ApplicationListener> applicationListeners) {
		this.applicationListeners = applicationListeners;
	}


}