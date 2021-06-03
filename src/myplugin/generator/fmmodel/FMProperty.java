package myplugin.generator.fmmodel;


public class FMProperty extends FMElement {
    //Property type
    private FMType type;
    // Property visibility (public, private, protected, package)
    private String visibility;
    //Multiplicity (lower value)
    private Integer lower;
    //Multiplicity (upper value)
    private Integer upper;

    // Added by: Janko
    private Boolean reference; // True - u pitanju je referenca na neku drugu klasu
    private String referenceType;    // Stavlja se na klase koje su na krajevima veze, govori o tipu veze

    /**
     * @ToDo: Add length, precision, unique... whatever is needed for ejb class generation
     * Also, provide these meta-attributes or tags in the modeling languange metaclass or
     * stereotype
     */


    public FMProperty(String name, FMType type, String visibility, int lower, int upper, Boolean reference,
					  String referenceType) {
        super(name);
        this.type = type;
        this.visibility = visibility;

        this.lower = lower;
        this.upper = upper;

        //Added by Janko
        this.reference = reference;
        this.referenceType = referenceType;
    }

    public FMType getType() {
        return type;
    }

    public void setType(FMType type) {
        this.type = type;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Integer getLower() {
        return lower;
    }

    public void setLower(Integer lower) {
        this.lower = lower;
    }

    public Integer getUpper() {
        return upper;
    }

    public void setUpper(Integer upper) {
        this.upper = upper;
    }


    public Boolean getReference() {
        return reference;
    }

    public void setReference(Boolean reference) {
        this.reference = reference;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

}
