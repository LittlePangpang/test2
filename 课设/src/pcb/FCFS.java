package pcb;

import java.util.LinkedList;
import java.util.Queue;

public class FCFS {

	
	public static void main(String[] args) {
		System.out.println("当前时间\t"+"名字\t"+"到达时间\t"+"运行时间\t"+"状态");
		Queue<PCB> queue = new LinkedList<PCB>();
		int time=1;
		PCB a1=new PCB("a1");
		PCB a2=new PCB("a2");
		PCB a3=new PCB("a3");
		PCB a4=new PCB("a4");
		PCB a5=new PCB("a5");
		while(time<=25){
			if(time==a1.getArrivalTime()) queue.offer(a1);
			if(time==a2.getArrivalTime()) queue.offer(a2);
			if(time==a3.getArrivalTime()) queue.offer(a3);
			if(time==a4.getArrivalTime()) queue.offer(a4);
			if(time==a5.getArrivalTime()) queue.offer(a5);
			if(queue.isEmpty()&&time<=25){
				System.out.println(time+"\t无运行进程。\r");
				time++;
			}
		
		if(!queue.isEmpty()) {

			if(!queue.isEmpty()) {
				//遍历队列，设置进程状态
				for(PCB q : queue) {
					if(q.equals(queue.peek())) {
						//队首
						q.setStatus("Working");
					}else {
						//非队首
						if(q.getStatus().equals("Working")) {
							q.setStatus("Ready");
						}												
					}
					//打印信息
					System.out.print(time+"\t");
					q.printInformationFCFS();
				}
				//进程运行
				queue.peek().run();
				time++;
			}
			
			for(PCB q : queue) {
				if(q.getRunTime() == 0) {
					q.setStatus("End");					
				}
												
				if(q.getStatus().equals("End")) {
					int turnoverTime=time-q.getArrivalTime();
					q.setturnoverTime(turnoverTime);
					System.out.println();
					//打印信息
					System.out.print("\t");
					q.printInformationFCFS();
					System.out.print("*********"+q.getName()+"进程结束,周转时间为");
					System.out.print(time-q.getArrivalTime()+"*********");
					queue.poll();
					break;
				}			
			}			
			System.out.println();	
			}				
		}
		System.out.println("平均周转时间="+(a1.getturnoverTime()+a2.getturnoverTime()+a3.getturnoverTime()+a4.getturnoverTime()+a5.getturnoverTime())/5);
	}
}
