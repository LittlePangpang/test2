package pcb;

import java.util.LinkedList;
import java.util.Queue;

public class Priority1 {
	public static void getMax(Queue<PCB> queue) {
		//找出优先级最高（优先级数最小）的进程
		int minPriorityNum = 100;
		int minArrivalTime = 100;
		for(PCB q : queue ) {
			if(q.getPriorityNum() < minPriorityNum) {
				minPriorityNum = q.getPriorityNum();	
			}			
		}
		//找出优先级最高且到达时间最早的进程
		for(PCB q : queue ) {
			if(q.getPriorityNum() == minPriorityNum && q.getArrivalTime() < minArrivalTime) {
				minArrivalTime = q.getArrivalTime();
			}
		}
		
		PCB first = queue.peek();
		for(int i = 0;i < queue.size();i++) {
			while(queue.peek().getPriorityNum() > minPriorityNum) {
				queue.offer(queue.peek());
				queue.poll();	
			}
			PCB q =  ((LinkedList<PCB>) queue).get(i);
			
			if(!q.equals(first)) {				
				if((q.getPriorityNum() == queue.peek().getPriorityNum()) && q.getArrivalTime() < queue.peek().getArrivalTime()) {					
					while(queue.peek().getArrivalTime() != minArrivalTime) {
						queue.offer(queue.peek());
						queue.remove(queue.peek());
						queue.remove(0);
						//i--;
					}										
				}else {						
					continue;
				}				
			}else {
				continue;
			}
		}				
	}
	
	public static void main(String[] args) {
		System.out.println("当前时间\t"+"名字\t"+"到达时间\t"+"运行时间\t"+"优先级数\t"+"状态");
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
			//把优先级最高（优先级数小）的进程调到队首
			if(queue.size() > 1) {
				getMax(queue);			
			}
			
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
					q.printInformation();
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
					System.out.println();
					//打印信息
					System.out.print("\t");
					q.printInformation();
					System.out.print("*********"+q.getName()+"进程结束,周转时间为");
					System.out.print(time-q.getArrivalTime()+"*********");
					queue.poll();
					break;
				}			
			}			
			System.out.println();	
			}				
		}
		
	}
}

