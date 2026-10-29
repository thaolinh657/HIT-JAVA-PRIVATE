import java.util.ArrayList;
import java.util.Scanner;

public class btnho {
    static void main() {
                ArrayList<Double> scores = new ArrayList<>();
                Scanner scanner = new Scanner(System.in);

                System.out.println("Vui lòng nhập 5 điểm:");
                for (int i = 1; i <= 5; i++) {
                    System.out.print("Nhập điểm lần " + i + ": ");
                    double score = scanner.nextDouble();
                    if (score < 0 || score > 10) {
                        System.out.println("Điểm không hợp lệ, đã bỏ qua!");
                        continue;
                    }

                    addScore(scores, score);
                }

                System.out.println("-------------------------");
                printScores(scores);
                System.out.println("Điểm trung bình: " + calculateAverage(scores));
                System.out.println("Số điểm đạt (>= 5): " + countPassed(scores));

                scanner.close();
            }
            public static void addScore(ArrayList<Double> list, double score) {
                list.add(score);
            }
            public static void printScores(ArrayList<Double> list) {
                System.out.print("Danh sách điểm hợp lệ: ");
                for (double score : list) {
                    System.out.print(score + " ");
                }
                System.out.println();
            }
            public static double calculateAverage(ArrayList<Double> list) {
                if (list.isEmpty()) {
                    return 0.0;
                }
                double sum = 0;
                for (double score : list) {
                    sum += score;
                }
                return sum / list.size();
            }
            public static int countPassed(ArrayList<Double> list) {
                int count = 0;
                for (double score : list) {
                    if (score >= 5.0) {
                        count++;
                    }
                }
                return count;
            }
}
