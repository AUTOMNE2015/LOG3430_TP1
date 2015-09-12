public interface ISuiteChainee {
	void add(ElementSuite nouvelElement);
	void removeAt(int position);
	void removeItem(int element);
	void setAt(int nouvelElement, int position);
	ElementSuite getAt(int position);
	int getSize();
	void reset();
	boolean isValid();
}