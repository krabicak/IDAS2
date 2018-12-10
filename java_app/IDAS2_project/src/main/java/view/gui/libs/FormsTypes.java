package view.gui.libs;

public enum FormsTypes {
    PREZENCNI("prezenční"), KOMBINOVANA("kombinovaná"), PREZENCNI_A_KOMBINOVANA("prezenční a kombinovaná");

    private String popis;

    FormsTypes(String popis) {
        this.popis = popis;
    }

    @Override
    public String toString() {
        return popis;
    }
}
