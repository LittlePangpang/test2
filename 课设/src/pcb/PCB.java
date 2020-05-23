package pcb;

public class PCB {
	private String name;//进程名字
	private int arrivalTime;//到达时间,缺省则为1~5的随机数
	private int priorityNum;//优先级，缺省则为1~10的随机数
	private int runTime;//运行时间，缺省则为1~5的随机数
	private String status = "Ready";//进程状态，默认为ready，队首执行时设为run
	private double turnoverTime = 0;//周转时间，于进程结束时赋值

	public PCB(String name) {
		super();
		this.name = name;
		this.setArrivalTime(arrivalTime);
		this.setPriorityNum(priorityNum);
		this.setRunTime(runTime);
		//this.printInformation();	
	}
	
	//打印PCB信息
	public void printInformation() {
		System.out.println(this.getName()+"\t"+this.getArrivalTime()+"\t"+
				this.getRunTime()+"\t"+this.getPriorityNum()+"\t"+this.getStatus());
	}
	public void printInformationPriority() {
		System.out.print(this.getName()+"\t"+this.getArrivalTime()+"\t"+
				this.getRunTime()+"\t"+this.getPriorityNum()+"\t"+this.getStatus());
	}
	public void printInformationFCFS() {
		System.out.println(this.getName()+"\t"+this.getArrivalTime()+"\t"+
				this.getRunTime()+"\t"+this.getStatus());
	}
	//进程运行
	public void run() {		
		this.priorityNum++;
		this.runTime--;		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		arrivalTime = (int) (Math.random()*5+1);
		this.arrivalTime = arrivalTime;
	}

	public int getPriorityNum() {
		return priorityNum;
	}

	public void setPriorityNum(int priorityNum) {
		priorityNum = (int) (Math.random()*10+1);
		this.priorityNum = priorityNum;
	}

	public int getRunTime() {
		return runTime;
	}

	public void setRunTime(int runTime) {
		runTime = (int) (Math.random()*5+1);
		this.runTime = runTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
	public void setturnoverTime(double turnoverTime) {
		this.turnoverTime = turnoverTime;
	}
	public double getturnoverTime() {
		return turnoverTime;
	}
}

