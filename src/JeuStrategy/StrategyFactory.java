package JeuStrategy;

public class StrategyFactory {
    public static CombatStrategy getStrategy(String type) {
        if (type == null) return new RandomStrategy();
        switch (type.toLowerCase()) {
            case "fast":
                return new FastAttackStrategy();
            case "heavy":
                return new HeavyAttackStrategy();
            case "defensive":
                return new DefensiveStrategy();
            case "random":
            default:
                return new RandomStrategy();
        }
    }
}
