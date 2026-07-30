import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BoundedStackTest {
     private static int passed = 0;
    private static int failed = 0;

    /** helper กลาง — พิมพ์ PASS/FAIL และนับผลให้เอง */
    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }
    }

    public static void main(String[] args) {
        boolean assertsOn = false;
        assert assertsOn = true;
        if (!assertsOn) {
            System.out.println("WARNING: assertions disabled"
                    + " - re-run with: java -ea BoundedStackTest\n");
        }

        System.out.println("===  BoundedStack Suite ===\n");
        testCreators();
        testpush();
        testPop();
        testpeek();
        testExposure();




        
        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) {
            System.exit(1);
        }
    }
 
    private static void testCreators() {
        System.out.println("-- Creators --");

        BoundedStack empty = new BoundedStack(0);
        check("new() -> empty", empty.size() == 0);
        
         // boundary: list ว่างคือขอบล่างที่ถูกต้อง
        BoundedStack isEmpty = new BoundedStack(0);
        check("new(isEmpty List) -> isEmpty", isEmpty.size()==0);
    }

    //====== Mutator : push ต้อง
    private static void testpush(){
        System.out.println("-- Push --");
        BoundedStack S = new BoundedStack(50);
        check("Push(A) -> return true", S.push("A"));
        check("Push(A) -> size 1", S.size() == 1);
        S.push("B");
        S.push("C");
        check("add preserves insertion order", S.Stack().equals(Arrays.asList("A", "B", "C")));

         boolean threwEmpty = false;
        try {
            S.push("");
        } catch (IllegalArgumentException e) {
            threwEmpty = true;
        }
        check("add(empty string) -> throws IllegalArgumentException", threwEmpty);
        boolean threwNull = false;
        try {
            S.push(null);
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("add(null) -> throws IllegalArgumentException", threwNull);
        
        // boundary: เติมจนเต็มพอดีแล้วเติมเพิ่ม
        BoundedStack full = new BoundedStack(50);
        for (int i = 0; i < 50 ; i++) {
            full.push("song" + i);
        }
        check("can fill up to CAPACITY", full.size() == 50);
        check("add when full -> returns false", !full.push("one more"));
        check("full Boundedstack stays at CAPACITY", full.size() == 50);



    }
     private static void testPop(){
        System.out.println("-- Pop --");
        BoundedStack b = new BoundedStack(Arrays.asList("A","B","C"),3);
        check("pop(B) -> returns true", b.pop("B"));
        check("B is removed",!b.Stack().contains("B"));
        check("size -> 2", b.size() == 2);
        check("remaining data",b.Stack().equals(Arrays.asList("A", "C")));

        check("remove missing BoundedStack -> returns false", !b.pop("nope"));
        check("failed remove leaves size unchanged", b.size() == 2);

        b.pop("A");
        b.pop("C");
        check("remove all -> empty", b.size() == 0);
        check("remove on empty BoundedStack -> returns false", !b.pop("A"));
     }
     private static void testpeek() {
        System.out.println("\n-- peek --");

        BoundedStack s = new BoundedStack(Arrays.asList("A", "B", "C"),3);
        check("size reports 2", s.size() == 3);
        check("contains finds an existing BoundedStack", s.Stack().contains("A"));
        check("contains rejects a missing BoundedStack", !s.Stack().contains("Z"));
        check("BoundedStack returns the full list in order",s.Stack().equals(Arrays.asList("A", "B", "C")));

        int before = s.size();
        s.size();
        s.Stack().contains("A");
        s.Stack();s.Stack();
        check("observers have no side effects", s.size() == before);

        // --- ทดสอบว่าไม่เกิด representation exposure ---

     }

         private static void testExposure() {
        System.out.println("\n-- Representation Exposure --");

        // ขาออก: แก้ list ที่ได้จาก Stack() ต้องไม่กระทบ rep
        BoundedStack s = new BoundedStack(50);
        s.push("A");

        List<String> got = s.Stack();
        got.clear();
        check("clearing result of Stack() does not affect BoundedStack",
                s.size() == 1);

        got = s.Stack();
        got.add("injected");
        check("adding to result of Stack() does not affect BoundedStack",
                s.size() == 1 && !s.Stack().contains("injected"));

        // สองครั้งต้องเป็นคนละ object
        check("songs() returns a fresh list each call",
                s.Stack() != s.Stack());

        // ขาเข้า: แก้ list ที่ส่งให้ constructor ต้องไม่กระทบ rep
        List<String> input = new ArrayList<String>(Arrays.asList("A", "B"));
        BoundedStack p = new BoundedStack(input,50);

        input.clear();
        check("clearing constructor argument does not affect ",
                p.size() == 2);

        input.add("injected");
        check("adding to constructor argument does not affect BoundedStack",
                !p.Stack().contains("injected"));
    }
}