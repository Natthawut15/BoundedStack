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
     
        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total : " + (passed + failed));
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");

        if (failed > 0) {
            System.exit(1);
        }
    }
    /**
     * Test Creator เมื่อสร้างเเล้ว Stack ต้องเท่ากับ 0
     * ตรวจสอบว่าใน Stack ว่างไหม
     * */

    private static void testCreators() {
        System.out.println("-- Creators --");

        BoundedStack empty = new BoundedStack(0);
        check("new() -> empty", empty.size() == 0);
        
         // boundary: list ว่างคือขอบล่างที่ถูกต้อง
        BoundedStack isEmpty = new BoundedStack(0);
        check("new(isEmpty List) -> isEmpty", isEmpty.size()==0);
    }

}
