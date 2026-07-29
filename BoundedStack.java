import java.util.*;
/**
 * BoundedStack นี้คือ การเก็บรายชื่อเเละไม่เก็บได้ไม่เกินความจุที่มี สร้าง Stack เพื่อใช้เก็บชื่อ ขึ้นมาเเละ CAPACITY เพื่อกำหนดขนาดที่จะเก็บได้
 * มีการตรวจสอบเงื่อนไขว่า Stack ต้องไม่เป็น Null , ไม่ว่าง , ไม่เกิน , CAPACITY
 * การเพิ่มข้อมูล มีการตรวจสอบว่า ถ้าข้อมูลที่เพิ่มเป็น Null , ค่าว่าง , เพิ่มข้อมูลตอนเต็มจะโยน Exception กลับ ถ้าผ่านหมดจะสามารถเพิ่มข้อมูลได้
 * การลบข้อมูล มีการตรวจสอบ ถ้า Stack ว่างจะโยน Exception มีตัวเเปรไว้เก็บข้อมูลที่ถูกลบไป เเละ return ค่านั้นกลับ
 * การดูข้อมูล มีการตวรสอบ ถ้าข้อมูลว่าง จะโยน Exception return ค่าที่อ่านได้กลับไป
 * ตรวจสอบว่า Stack ว่างไหม ถ้า Stack ไม่ว่าง retun false ถ้าว่าง return true
 * ตรวจสอบว่า Sack เต็มหรือยัง ถ้ายังไม่เต็ม return false ถ้าเต็ม return true
 * ดูขนาดของ Stack
 * ดูความจุ
 * การ Clear Stack ตรวจสอบว่า ถ้า Stack ว่างโยน Exception ถ้ามีข้อมูลใน Stack จึงจะให้ลบได้
 */
public class BoundedStack {

    private final List<String> Stack;
    private final int CAPACITY;
    
    // AF(Stack,CAPACITY) คิอ คลาสนี้ป็นการเก็บชื่อจริงของ User
    // เละไม่เกินค่าที่กำหนด
    // RI
    // ชื่อต้องไม่เป็น Null
    // ชื่อต้องไม่เป็นชื่อว่าง
    // เก็บรายชื่อได้ไม่เกิน CAPACITY

    public void checkRep() {
        assert Stack != null : "Stack is not Null";
        assert !Stack.isEmpty();
        assert Stack.size() <= CAPACITY ;
    }
    
        // ======= Creater =======
    /**
     * 
     * @param CAPACITY ขนาดของ Stack
     */
    public BoundedStack(int CAPACITY) {
        this.Stack = new ArrayList<>();
        this.CAPACITY = CAPACITY;
        checkRep();
    } 
    /**
     * 
     * @param initial รายชื่อเริ่มต้น ต้องไม่เกิน CAPACITY
     * @param CAPACITY ขนาดของ Stack
     */
    public BoundedStack(List<String> initial,int CAPACITY){
        if(initial == null) throw new IllegalArgumentException();
        if(initial.size()> CAPACITY) throw new IllegalArgumentException();
        this.Stack = new ArrayList<>(initial);
        this.CAPACITY = CAPACITY;
        checkRep();
    }
     // ======= Mutator =======
    /**
     * param name เพิ่มรายชื่อ
     * @t
     * @hrows IllegalArgumentException ถ้า name เป็น Null , ถ้า name ว่าง , ถ้า Stack เต็ม 
     */
    public boolean push(String name) {
        if(name == null) throw new IllegalArgumentException();
        if(name.isEmpty()) throw new IllegalArgumentException();
        if(Stack.size()== CAPACITY) return false;
        Stack.add(name);
        return true;
    }
    /**
     * clear Stack
     */
    public void clear(){
        if(Stack.size() <= 0) throw new IllegalArgumentException();
        Stack.clear();
        checkRep();
    }
     //====== Producers ======        
    /**
     * 
     * @throws IllegalArgumentException ถ้าข้อมูลที่จะลบไม่มีใน Stack
     * @return ข้อมูลที่ลบไป
     */
    public boolean pop(String Stacks){
        if(Stack.isEmpty()) throw new IllegalArgumentException();
        if(!Stack.contains(Stacks)) return false;
        Stack.remove(Stacks);
        String remove = Stack.get(Stack.size()-1);
        Stack.remove(Stack.size()-1);
        System.out.println(remove);
       return true;
    }
    /**
     * @return ข้อมูลตัวสุดท้ายใน Stack
     * @throws IllegalArgumentException ถ้า Stack ว่าง
     */
    public String peek(){
        if(Stack.isEmpty()) throw new IllegalArgumentException();
        String peek = Stack.get(Stack.size()-1) ;
        return peek;
    }
}