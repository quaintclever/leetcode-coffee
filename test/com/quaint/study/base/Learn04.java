package com.quaint.study.base;

/**
 * @author quaint
 * @date 2021/3/24
 */
public class Learn04 {

    public static void main(String[] args) {
        Learn04 learn04 = new Learn04();
        learn04.muTable();
    }

    // 2. 打印99乘法表
    public void muTable()  {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%d * %d = %d\t",j,i,i*j);
            }
            System.out.println();
        }
    }

    public void continueDemo() {
        forloop1:
        for (int i = 0; i < 5; i++) {
            // forloop2:
            for (int j = 0; j < 5; j++) {
                if (i == 2 && j == 2) {
                    continue forloop1;
                }
                System.out.printf("%d-%d\n", i, j);
            }
        }
    }

    public void gotoDemo2() {
        // 标签
        breakTag:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 2) {
                    // 设置退出标签
				    break breakTag;
                }
                System.out.printf("%d-%d\n", i, j);
            }
        }
    }

    public void gotoDemo1() {
        boolean breakFlag = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 2) {
                    // 设置退出标签
                    breakFlag = true;
                    break;
                }
                System.out.printf("%d-%d\n", i, j);
            }
            // 外层for循环判断
            if (breakFlag) {
                break;
            }
        }
    }
    
    private void ifDemo1() {
        System.out.println("\n------- if else练习 -------\n");
        int score = 65;
        if (score >= 90) {
            System.out.println("恭喜你, 优秀\n");
        } else if (score >= 60) {
            System.out.println("恭喜你, 及格了!");
        } else {
            System.out.println("不及格!");
        }
    }

    public void switchDemo1() {
        int finger = 3;
        switch(finger) {
            case 1:
                System.out.println("大拇指");
                break;
            case 2:
                System.out.println("食指");
                break;
            case 3:
                System.out.println("中指");
                break;
            case 4:
                System.out.println("无名指");
                break;
            case 5:
                System.out.println("小拇指");
                break;
            default:
                System.out.println("无效的输入！");
        }
    }

}
