import enums.SizeWeight;
import items.*;
import people.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        RobinsonCrusoe robinson = new RobinsonCrusoe();

        robinson.bigAxes = Axe.createThreeBigAxes();
        robinson.smallTradeAxes = Axe.createManySmallTradeAxes();
        robinson.grindstone = new Grindstone(SizeWeight.LARGE_and_HEAVY);

        System.out.println(robinson.thinkAboutHandsProblem());
        System.out.println(robinson.thinkAboutProblem());

        ArrayList<Tree> trees = new ArrayList<>();
        for (int i = 0; i < 3; i++) trees.add(Tree.randomTree("Дерево№" + (i + 1)));

        System.out.println("\nРубка деревьев:");
        for (Tree t : trees) {
            for (Axe axe : robinson.bigAxes) {
                try {
                    System.out.println("[" + t.type() + "] " + axe.chopTree(t));
                } catch (ToolConditionException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.out.println("\nПопытка точить, вращая точило руками:");
        robinson.grindstone.canBeRotatedByHand = robinson.grindstone.sizeWeight != SizeWeight.LARGE_and_HEAVY;

        try {
            System.out.println(robinson.grindstone.rotate());
            System.out.println(robinson.grindstone.sharpenAxe(robinson.bigAxes.get(0)));
        } catch (IllegalStateException ex) {
            System.out.println("Непредвиденная ошибка: " + ex.getMessage());
        } catch (ToolConditionException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nСборка и использование ножного механизма:");
        robinson.solution = robinson.buildFootOperatedMechanism();
        System.out.println(robinson.realizeGrindstoneProblem());
        System.out.println(robinson.solution.operateWithFoot());

        System.out.println("\nЗаточка:");
        for (Axe axe : robinson.bigAxes) {
            try {
                System.out.println(robinson.useTool(axe));
                System.out.println(robinson.grindstone.sharpenAxe(axe));
            } catch (ToolConditionException e) {
                System.out.println(e.getMessage());
            } catch (IllegalStateException ex) {
                System.out.println("Непредвиденная ошибка: " + ex.getMessage());
            }
        }

        System.out.println("\nСравнение умственных усилий:");
        Statesman statesman = new Statesman();
        System.out.println(statesman.thinkAboutPoliticalQuestion("Почему Трамп похитил президента Венесуэлы?"));

        MentalEnergyComparator cmp = new MentalEnergyComparator();
        System.out.println(cmp.compare(robinson, statesman));

        HandsSolutionAnalyzer analyzer = new HandsSolutionAnalyzer(robinson, robinson.grindstone, robinson.solution);
        System.out.println("\nАнализ решения:");
        System.out.println(analyzer.analyzeSolution());
    }
}
