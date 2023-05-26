package simpleController20.core.view.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import simpleController20.api.view.event.ViewContainerEvent;
import simpleController20.api.view.event.ViewContainerEventController;
import simpleController20.core.core.AbstractApplication;

/**
 * Default implementation of the {@link ViewContainerEventController}
 * 
 * @author mgg
 *
 */
public class DefaultViewContainerEventController implements ViewContainerEventController{
	
	/* (non-Javadoc)
	 * @see org.viewaframework.view.event.ViewContainerEventController#onViewClose(org.viewaframework.view.event.ViewContainerEvent)
	 */
	private static final Logger logger = LoggerFactory.getLogger(DefaultViewContainerEventController.class);
	private void debugJustInCase(String message) {
		if (logger.isDebugEnabled()) {
			logger.debug(message);
		}
	}
	@Override
	public void onViewClose(ViewContainerEvent event) {
		debugJustInCase("==========on_view_close==================");
		
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.view.event.ViewContainerEventController#onViewInit(org.viewaframework.view.event.ViewContainerEvent)
	 */
	@Override
	public void onViewInit(ViewContainerEvent event) {
		
		debugJustInCase("======on_view_init======================");
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.view.event.ViewContainerEventController#onViewInitUIState(org.viewaframework.view.event.ViewContainerEvent)
	 */
	@Override
	public void onViewInitUIState(ViewContainerEvent event) {}

	/* (non-Javadoc)
	 * @see org.viewaframework.view.event.ViewContainerEventController#onViewInitBackActions(org.viewaframework.view.event.ViewContainerEvent)
	 */
	@Override
	public void onViewInitBackActions(ViewContainerEvent event) {}

	/* (non-Javadoc)
	 * @see org.viewaframework.view.event.ViewContainerEventController#onViewFinalUIState(org.viewaframework.view.event.ViewContainerEvent)
	 */
	@Override
	public void onViewFinalUIState(ViewContainerEvent event) {}
}