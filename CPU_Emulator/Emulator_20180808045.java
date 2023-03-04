package ComputerOrganization;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Arda Atasoy
 * @version jdk 17
 * @since 5/20/2022(american calender)
 */

public class Emulator_20180808045 {


    public static void main(String[] args) throws FileNotFoundException {

        Scanner processLine = new Scanner(new File(args[0]));
        ArrayList<InstructionSet> instructions = new ArrayList<>();
        ArrayList<Integer> operands = new ArrayList<>();

        while (processLine.hasNext()) {

            String[] processLineParts = processLine.nextLine().split(" ");

            if (processLineParts.length == 2) {
                instructions.add(InstructionSet.valueOf(processLineParts[1]));
                operands.add(0);
            }
            if (processLineParts.length == 3) {

                instructions.add(InstructionSet.valueOf(processLineParts[1]));
                operands.add(Integer.parseInt(processLineParts[2]));

            }
        }

        processLine.close();

        CpuEmulator emulator = new CpuEmulator(instructions, operands);
        emulator.process();


    }
}

enum InstructionSet {

    START {
        public String toString() {
            return "START";
        }
    },


    LOAD {
        public String toString() {
            return "LOAD";
        }
    },

    LOADM {
        public String toString() {
            return "LOADM";
        }
    },

    STORE {
        public String toString() {
            return "STORE";
        }
    },

    CMPM {
        public String toString() {
            return "CMPM";
        }
    },

    CJMP {
        public String toString() {
            return "CJMP";
        }
    },

    JMP {
        public String toString() {
            return "JMP";
        }
    },

    ADD {
        public String toString() {
            return "ADD";
        }
    },

    ADDM {
        public String toString() {
            return "ADDM";
        }
    },

    SUB {
        public String toString() {
            return "SUB";
        }
    },

    SUBM {
        public String toString() {
            return "SUBM";
        }
    },

    MUL {
        public String toString() {
            return "MUL";
        }
    },

    MULM {
        public String toString() {
            return "MULM";
        }
    },

    DISP {
        public String toString() {
            return "DISP";
        }
    },

    HALT {
        public String toString() {
            return "HALT";
        }
    }
}

class CpuEmulator {

    ArrayList<InstructionSet> instructions;
    ArrayList<Integer> operands;
    int IR = 0;
    int[] M = new int[256];
    int AC = 0;
    int Flag = 0;
    boolean Halt;


    public CpuEmulator(ArrayList<InstructionSet> instructions, ArrayList<Integer> operands) {

        this.instructions = instructions;
        this.operands = operands;

    }

    public void process() {


        if (!instructions.contains(InstructionSet.START)) {
            System.exit(0);
        }

        while (!instructions.get(0).equals(InstructionSet.START)) {
            instructions.remove(0);
            operands.remove(0);
        }


        while (!Halt) {

            InstructionSet instruction;
            int operand;
            instruction = instructions.get(IR);
            operand = operands.get(IR);

            IR++;

            switch (instruction) {

                case START:
                    break;
                case LOAD:
                    LOAD(operand);
                    break;
                case LOADM:
                    LOADM(operand);
                    break;
                case STORE:
                    STORE(operand);
                    break;
                case CMPM:
                    CMPM(operand);
                    break;
                case CJMP:
                    CJMP(operand);
                    break;
                case JMP:
                    JMP(operand);
                    break;
                case ADD:
                    ADD(operand);
                    break;
                case ADDM:
                    ADDM(operand);
                    break;
                case SUB:
                    SUB(operand);
                    break;
                case SUBM:
                    SUBM(operand);
                    break;
                case MUL:
                    MUL(operand);
                case MULM:
                    MULM(operand);
                    break;
                case DISP:
                    DISP();
                    break;
                case HALT:
                    HALT();
                    break;

                default:
                    System.exit(0);
                    break;
            }
        }
    }

    public void LOAD(int X) {
        AC = X;
    }

    public void LOADM(int line) {
        AC = M[line];
    }

    public void STORE(int line) {
        M[line] = AC;
    }

    public void CMPM(int line) {
        Flag = Integer.compare(AC, M[line]);
    }

    public void CJMP(int X) {
        if (Flag > 0) {
            IR = X;
        }
    }

    public void JMP(int X) {
        IR = X;
    }

    public void ADD(int X) {
        AC += X;
    }

    public void ADDM(int line) {
        AC += M[line];
    }

    public void SUBM(int line) {
        AC -= M[line];
    }

    public void SUB(int X) {
        AC += X;
    }

    public void MUL(int X) {

        AC *= X;

    }

    public void MULM(int line) {
        AC *= M[line];
    }

    public void DISP() {

        System.out.println("AC: " + AC);

    }

    public void HALT() {

        Halt = true;

    }
}
