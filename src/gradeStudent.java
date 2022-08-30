import java.util.Scanner;
import java.text.DecimalFormat;

public class gradeStudent {
    static final int MAX = 100;
    static DecimalFormat df = new DecimalFormat("#.0");
    private static int ScoreShift, shiftAmountScore, sectionTotal, checkweight, ScoreEarned, weight;
    static double grade;

    //hàm main() điều khiển luồng chính của chương trình.
    public  static void main(String [] args) {

        //Hàm begin() để hiển thị thông điệp chào mừng.
        begin();
        //Hàm midTerm() để nhập và tính toán điểm thi giữa kỳ.
        midTerm();
        //Hàm finalTerm() để nhập và tính toán điểm thi cuối kỳ.
        finalTerm();
        //Hàm homework() để nhập và tính toán điểm bài tập về nhà.
        homework();
        //Hàm report() để tính toán về hiển thị kết quả GPA cũng như thông báo nhận xét tương ứng.
        report();
    }
    /*--------------------------------------kết thúc luồng code hàm main---------------------------------------------*/

    //Hàm begin() để hiển thị thông điệp chào mừng.
    public static void begin(){
        System.out.println("This program reads exam/homework scores and reports your overall course grade");
    }

    //Hàm midTerm() để nhập và tính toán điểm thi giữa kỳ.
    public static void midTerm(){
        System.out.println("Midterm:");
        inputWeight();
        inputScoreEarned();
        inputScoreShift();
    }

    //Hàm finalTerm() để nhập và tính toán điểm thi cuối kỳ.
    public static void finalTerm(){
        System.out.println("Final:");
        inputWeight();
        inputScoreEarned();
        inputScoreShift();
    }

    //Hàm homework() để nhập và tính toán điểm bài tập về nhà.
    public static void homework() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Homework:");
        //điều kiện tổng weight phải bằng 100 -- yêu cầu người dùng nhập lại
        if (checkweight > 100) {
            System.out.println("Weight's total must be 100, pls recheck all Weight!!");
            checkweight = 0;
            midTerm();
        }
        inputWeight();
        if (checkweight != 100) {
            System.out.println("Weight's total must be 100, pls recheck all Weight!!");
            checkweight = 0;
            midTerm();
        }
        //tổng điểm asm của người dùng
        System.out.print("Number of assignments? ");
        int n = sc.nextInt();
        int max , min, totalPoint, maxCodeHW;
        int maxTotal = 0, minTotal = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Assignment" + " " + (i + 1) + " " + "score and max? ");
            min = sc.nextInt();
            max = sc.nextInt();

            //tổng điểm max và min sau mỗi vòng lặp
            maxTotal += max;
            minTotal += min;
        }
        //test code
//        System.out.println(maxTotal);
//        System.out.println(minTotal);

        //nhập điểm chuyên cần từ màn hình
        System.out.print("How many sections did you attend? ");
        //tính điểm chuyên cần
        int section = sc.nextInt();
        sectionTotal = section * 5;
        if (sectionTotal > 30) {
            sectionTotal = 30;
        }
        System.out.println("Section points = " + " " + sectionTotal + " " + "/" + 30);

        //tổng điểm sẽ bằng điểm của bài asm + điểm chuyên cần
        totalPoint = minTotal + sectionTotal;
        //tính tổng điểm tối đa của phần homework = max + 30
        maxCodeHW = maxTotal + 30;
        if(maxCodeHW > 150){
            maxCodeHW = 150;
        }

        System.out.println("Total points = " + totalPoint + " " + "/" + " "+ maxCodeHW);

        double WeightedScore = (double) totalPoint / maxCodeHW * weight;
        System.out.println("Weighted score: = " + " " +(df.format(WeightedScore))+ " " + "\\" + " " + weight);
        grade += WeightedScore;
    }

    //Hàm report() để tính toán về hiển thị kết quả GPA cũng như thông báo nhận xét tương ứng.
    public static void report(){

        //hàm tính Overall percentage
//        System.out.println(grade);
        System.out.println("Overall percentage = " + df.format(grade));

        //hàm tính Grade max
        if (grade >= 85){
            System.out.println("Your grade will be at least: 3.0");
            System.out.println("Message: Excellent, you are an amazing :)");
        }
        if (grade < 85 && grade >= 75){
            System.out.println("Your grade will be at least: 2.0");
            System.out.println("Message: Just a little harder");
        }
        if (grade < 75 && grade >= 60){
            System.out.println("Your grade will be at least: 1.0");
            System.out.println("Message: I know, you can do better. Don't give up!!");
        }
        if (grade < 60){
            System.out.println("Your grade will be at least: 0.0");
            System.out.println("Message: Don't give up. Let's try to work hard!!");
        }
    }
    /*--------------------------------------kết thúc hàm nằm trong main----------------------------------------------*/

    //hàm nhập điểm weight từ người dùng
    public static void inputWeight () {
        Scanner sc = new Scanner(System.in);
        System.out.print("Weight (0-100)? ");
        weight = sc.nextInt();

        // tính tổng weight ==> đảm bảo điều kiện tổng phải là 100
        checkweight += weight;
//      System.out.println(weightCount);

        if ( weight < 0 || weight > MAX ){
            System.out.println("Weight must be between 0 and 100");
            inputWeight();
        }
    }
    //hàm nhập điểm Score earned
    public static int inputScoreEarned(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Score earned? ");
        ScoreEarned = Math.abs(sc.nextInt());
        return ScoreEarned;
    }

    //hàm nhập Were scores shifted (1 = yes, 2=no)
    //kiểm tra dữ kiệu người dùng nhập có phải là 1 hay 2 ?
    //nếu 1 -> gọi hàm shiftAmount      -->gọi hàm totalPoint
    //nếu 2 -> bỏ qua hàm shiftAmount   -->gọi hàm totalPoint
    public static void inputScoreShift(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Were scores shifted (1 = yes, 2=no)? ");
        ScoreShift = sc.nextInt();

        //hiện hàm theo số mà người dùng nhập
        //bắt buộc phải chọn 1 hay 2
        if (ScoreShift != 1 && ScoreShift != 2){
            System.out.println("Pls choose 1 or 2");
            inputScoreShift();
        }
        if (ScoreShift == 2){
            totalPoint();
        }
        if (ScoreShift == 1){
            shiftAmount();
            return;
        }
    }
    //input Shift amount, số điểm được tăng
    //nhập điểm tăng --> gọi tiếp hàm totalPoint()
    public static void shiftAmount(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Shift amount? ");
        shiftAmountScore = sc.nextInt();
        totalPoint();
    }

    //hàm totalPoint
    //nếu chọn 1 --> totalPoint = shiftAmount + ScoreEarned
    //nếu chọn 2 --> totalPoint = ScoreEarned
    public static void totalPoint() {
        int n = 0;
        double score = 0.0;
        //khi người dùng nhập scores shifted  là 2 = no
        if (ScoreShift == 2) {
            n = ScoreEarned;
            score = (double) n / MAX * (double) weight;

            //biến dùng để tính grade - GPA ở hàm report
            grade += score;

            //khi người dùng nhập scores shifted  là 1 = yes
        }
        if(ScoreShift == 1) {
            n = ScoreEarned + shiftAmountScore;
            //tổng của scores shifted  và Score Earned lớn hơn 100 --> thì chỉ lấy 100
            if (n > MAX) n = MAX;

            score = ((double) n / MAX * (double) weight);
            grade += score;
        }

        System.out.print("Total points = " + n + " " + "\\" + " " + MAX + "\n");
        System.out.print("Weighted score = " + " " + (df.format(score)) + " " + "\\" + " " + weight + "\n");
    }
}
