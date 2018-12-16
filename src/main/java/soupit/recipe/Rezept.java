package soupit.recipe;

public class Rezept {
    private ZutatMengeEinheit[] zumeng;
    private String name;

    public  Rezept(String name, ZutatMengeEinheit...zumeng) {
        this.zumeng = zumeng;
        this.name = name;
    }


    int checkAccordance(Zutat...z) {
        int count =0;
        for(Zutat zut:z){
            for(ZutatMengeEinheit zum:zumeng){
                if (zum.getZutat().equals(zut)) count++;
            }
        }
        return count;
    }


    //public void addOptions(ZutatMengeEinheit...optionaleZutaten) {

    //}
    public String toString() {
        return name;
    }

    public ZutatMengeEinheit[] getZumeng() {
        return zumeng;
    }

    public void multiplyZumeng(int multiplicator){
        for(ZutatMengeEinheit zum:zumeng){
            zum.multiplyIngredients(multiplicator);
        }
    }

    //    this.optionaleZutaten = Arrays.copyOf(optionaleZutaten,optionaleZutaten.length);

}
