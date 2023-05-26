package simpleController20.api.core;

/**
 *  
* @author Mario Garcia
* @since 1.0
*
*/
//https://github.com/mariogarcia/viewa/blob/c39f7f46dc39908bd23cd4ded0b60c5f555617b8/api/src/main/java/org/viewaframework/core/ApplicationListener.java#L9
public interface ApplicationListener {

	/**
	 * @param event
	 */
	public void onClose(ApplicationEvent event);
	/**
	 * @param event
	 */
	public void onPrepare(ApplicationEvent event);
	/**
	 * @param event
	 */
	public void onPrepareUI(ApplicationEvent event);
	/**
	 * @param event
	 */
	public void onInitUI(ApplicationEvent event);
	
}