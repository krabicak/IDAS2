package view.gui.libs;

public enum SemesterTypes {
    ZIMNI("zimní"), LETNI("letní"), ZIMNI_A_LETNI("zimní a letní");

    private String popis;

    SemesterTypes(String popis) {
        this.popis = popis;
    }

    @Override
    public String toString() {
        return popis;
    }
}
