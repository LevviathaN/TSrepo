package utils.bpp_old;

/**
 * Anything that is a Doable implements this interface.
 * 
 * @author jchida
 *
 */
public interface Doable {

	default void setParent(AbstractDoable object){}

	/**
	 * <p>Every doable does some work. Define that work here, which may be
	 * mundane/complex heavy-lifting, or it may simply be calls into 
	 * other Doables this Doable is composed with. </p>
	 */
    void doIt();
	
	/**
	 * Do this work before doIt() runs.
	 */
    void preDo();
	
	/**
	 * Do this work after doIt() runs.
	 */
    void postDo();
}
