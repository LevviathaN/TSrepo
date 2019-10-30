package utils.bpp_old;

public class DoableBean extends AbstractDoable{

/** <p>From the Business Process Table, the following information exists:
 * 				//Col1 = Business Process
				//Col2 = OnFail
				//Col3 = Action
				//Col4 = Logical Name/Element Location
				//Col5 = Parameter </p>
 * We read in the following columns: 
 * @param stepId business process step id
 * @param doableOnFail action to execute on failure
 * @param uiAction web UI action taking place during test
 * @param elementLocation logical name / element location of element
 * @param parameters distinguishes a UI action that requires more user input
 */
	public DoableBean(String stepId, String doableOnFail, String uiAction, String elementLocation, String parameters) {
		this.stepId = stepId;
		this.doableOnFail = doableOnFail;
		this.uiAction = uiAction;
		this.elementLocation = elementLocation;
		this.parameters = parameters;
		
	}


	

}
