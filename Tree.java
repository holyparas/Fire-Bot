/**
 * info1103 - assignment 3
 * Paras Gathani
 * pgat6537
 */

public class Tree {

	private int height;
	private int fire;
	private boolean isBurnt;
	private boolean allBurning = false;
	private boolean fireReg = false;

	public Tree(int height) {
		this.height = height;
	}

	public int getHeight() {
		return this.height;
	}

	public int getFire() {
		return this.fire;
	}

	public void setFire() {
		this.fire = 1;
	}

	public void setFire(int num){
		this.fire++;
	}

	public void allBurning() {
		this.allBurning = true;
	}

	public void fireReg() {
		this.fireReg = true;
	}

	public boolean getFireReg() {
		return this.fireReg;
	}

	public boolean getAllBurning() {
		return this.allBurning;
	}

	public void isBurnt(boolean x) {
	 this.isBurnt = x;
	}

	public boolean getBurnt() {
		return this.isBurnt;
	}

	public void removeFire(){
		this.fire = 0;
	}





}

