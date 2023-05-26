package simpleController20.api.core;

import java.util.List;

/**
 * @author Mario García
 * @since 1.0
 *
 */
public interface ApplicationListenerAware {
	
	/**
	 * @param listener
	 */
	//not called from abstractApplication
	public void addApplicationListener(ApplicationListener listener);
	/**
	 * @param event
	 */
	
	//called only from AbstractApplication
	public void fireClose(ApplicationEvent event);
	
	/**
	 * @param event
	 */
	//not called from abstractApplication
	public void fireinitUI(ApplicationEvent event);
	/**
	 * @param event
	 */
	//not called from abstractApplication
	public void firePrepare(ApplicationEvent event);
	
	/**
	 * @param event
	 */
	//not called from abstractApplication
	public void firePrepareUI(ApplicationEvent event);
	/**
	 * @return
	 */
	//not called from abstractApplication
	public List<ApplicationListener> getApplicationListeners();
	/**
	 * @param listener
	 */
	//not called from abstractApplication
	public void removeApplicationListener(ApplicationListener listener);
	/**
	 * @param applicationListeners
	 */
	//not called from abstractApplication
	public void setApplicationListeners(List<ApplicationListener> applicationListeners);

}