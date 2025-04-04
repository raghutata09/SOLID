package SplitWiseCaseStudy.Expense;

import SplitWiseCaseStudy.Expense.Split.EqualExpenseSplit;
import SplitWiseCaseStudy.Expense.Split.ExpenseSplit;
import SplitWiseCaseStudy.Expense.Split.PercentageExpenseSplit;
import SplitWiseCaseStudy.Expense.Split.UnequalExpenseSplit;

public class SplitFactory {

    public static ExpenseSplit getSplitObject(ExpenseSplitType splitType) {

        switch (splitType) {
            case EQUAL:
                return new EqualExpenseSplit();
            case UNEQUAL:
                return new UnequalExpenseSplit();
            case PERCENTAGE:
                return new PercentageExpenseSplit();
            default:
                return null;
        }
    }
}

