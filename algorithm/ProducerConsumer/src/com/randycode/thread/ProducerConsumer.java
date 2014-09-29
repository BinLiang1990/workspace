package com.randycode.thread;

import java.util.Stack;

public class ProducerConsumer {

	private final int PROCUDTS_NO = 30;
	private Stack<Integer> items;
	private final int STACK_SIZE = 10;
	
	public ProducerConsumer() {
		items = new Stack<Integer>();
	}
	
	class Producer implements Runnable {

		private int count = 0;
		
		@Override
		public void run() {
			while(count < PROCUDTS_NO) {
				synchronized(items) {
					while(items.size() >= STACK_SIZE) {
						try {
							items.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					produce(++count);
					items.notifyAll();
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public void produce(int i) {
			System.out.println("produce: " + i);
			items.push(i);
		}
	}
	
	class Consumer implements Runnable {

		private int count = 0;
		
		@Override
		public void run() {
			while(count < PROCUDTS_NO) {
				synchronized(items) {
					while(items.empty()) {
						try {
							items.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					consume();
					items.notifyAll();
				}
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		public void consume() {
			++count;
			System.out.println("consume---: " + items.pop());
		}
	}
	
	public static void main(String[] args) {
		ProducerConsumer pc = new ProducerConsumer();
		Producer producer = pc.new Producer();
		Consumer consumer = pc.new Consumer();
		new Thread(producer).start();
		new Thread(producer).start();
		new Thread(producer).start();
		new Thread(consumer).start();
		new Thread(consumer).start();
	}
}
