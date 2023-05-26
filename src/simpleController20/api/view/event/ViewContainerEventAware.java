package simpleController20.api.view.event;


//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/api/src/main/java/org/viewaframework/view/event/ViewContainerEventAware.java
public interface ViewContainerEventAware {
	
	public void fireViewClose(ViewContainerEvent event);
	
	public void fireViewInit(ViewContainerEvent event);
	
	public void fireViewInitUIState(ViewContainerEvent event);
	
	public void fireViewInitBackActions(ViewContainerEvent event);
	
	public void fireViewFinalUIState(ViewContainerEvent event);

}