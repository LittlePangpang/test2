package pcb;

import java.util.LinkedList;
import java.util.Queue;

public class Priority {
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
		System.out.println("名字\t"+"到达时间\t"+"运行时间\t"+"优先级数\t"+"状态");
		Queue<PCB> queue = new LinkedList<PCB>();
//		queue.offer(new PCB("a1",1,9,9));
//		queue.offer(new PCB("a2",2,5,10));	
//		queue.offer(new PCB("a3",5,7,10));
//		queue.offer(new PCB("a4",2,4,6));
		queue.offer(new PCB("a1"));
		queue.offer(new PCB("a2"));	
		queue.offer(new PCB("a3"));
		queue.offer(new PCB("a4"));
		queue.offer(new PCB("a5"));

		while(!queue.isEmpty()) {
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
					q.printInformation();
				}
				//进程运行
				queue.peek().run();
			}
			
			for(PCB q : queue) {
				if(q.getRunTime() == 0) {
					q.setStatus("End");					
				}
												
				if(q.getStatus().equals("End")) {
					System.out.println();
					//打印信息
					q.printInformation();
					System.out.print("==============="+q.getName()+"进程结束================");
					queue.poll();
					break;
				}
			}			
			System.out.println();	
			}				
		}		
	}


