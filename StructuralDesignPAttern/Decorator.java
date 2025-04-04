package BehaviourDesignPattern;

public class Decorator {

    /*
      Maanlo hame cofee design krna h, jaise coffee base uppr se extra milk, extra sugar, chocolate syrup etc.
      Maanlo Pizza base h usme hame add krna h extra cheeze, capsicum, jalapeno etc..
      Iss case m ham alag alg class to bna ni skte for eg- BasePizzaCheese, BasePizzaMushrrom,
      BasePizzaMushroomJalapeeno,  BasePizzaJalapeeno etc.
      Aise bhut permutations ban jaynge and feasible ni h itne classes sambhalna
      uske lie decorator design ue kro

        BasePizza
            cost(return 200)

        VegFarmHouse extends Base Pizza
            cost(return 300;)

        Margarita extends Base Pizza
            cost(return 450)
        -------------

        ToppingDecorator


        ExtraCheese
            BasePizza bp;
            cost(return bp.cost()+50)

       ExtraMushroom
            BasePizza bp;
            cost(return bp.cost()+100)

     */
    public static void main(String[] args) {
        BasePizza bp = new ExtraCheese(new FarmHouse());
        System.out.print(bp.cost());
    }

}

public abstract class BasePizza{
    public abstract int cost();
}

public class FarmHouse extends BasePizza{
    @Override
    public int cost(){
        return 200;
    }
}
public class VegDelight extends BasePizza{
    @Override
    public int cost(){
        return 300;
    }
}


public abstract class ToppingDecorator extends BasePizza{

}

public class ExtraCheese extends ToppingDecorator{
    BasePizza bp;
    public ExtraCheese(BasePizza bp){
        this.bp = bp;
    }
    @Override
    public int cost(){
        return this.bp.cost() + 50;
    }
}

public class Mushroom extends ToppingDecorator{
    BasePizza bp;
    public ExtraCheese(BasePizza bp){
        this.bp = bp;
    }
    @Override
    public int cost(){
        return this.bp.cost() + 40;
    }
}

