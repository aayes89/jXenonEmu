/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jxenonemul.XCPU;

/**
 *
 * @author localadmin
 */
class SLBEntry {
        private int C, L, N, Kp, Ks;
        private long VSID, ESID;

        public int getC() {
            return C;
        }

        public void setC(int C) {
            this.C = C;
        }

        public int getL() {
            return L;
        }

        public void setL(int L) {
            this.L = L;
        }

        public int getN() {
            return N;
        }

        public void setN(int N) {
            this.N = N;
        }

        public int getKp() {
            return Kp;
        }

        public void setKp(int Kp) {
            this.Kp = Kp;
        }

        public int getKs() {
            return Ks;
        }

        public void setKs(int Ks) {
            this.Ks = Ks;
        }

        public long getVSID() {
            return VSID;
        }

        public void setVSID(long VSID) {
            this.VSID = VSID;
        }

        public long getESID() {
            return ESID;
        }

        public void setESID(long ESID) {
            this.ESID = ESID;
        }
    }
