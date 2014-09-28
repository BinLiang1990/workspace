package com.randycode.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PhilosopherDining {

	public static void main(String[] args) {
		int ponder = 3;
		int size = 5;
		ExecutorService exec = Executors.newCachedThreadPool();
		Chopstick[] sticks = new Chopstick[size];
		for (int i = 0; i < size; ++i) {
			sticks[i] = new Chopstick();
		}
		for (int i = 0; i < size; ++i) {
			if (i < size - 1) {
				exec.execute(new Philosopher(sticks[i], sticks[(i + 1) % size],
						i, ponder));
			} else {
				exec.execute(new Philosopher(sticks[0], sticks[i], i, ponder));
			}
		}
		exec.shutdown();
	}
}

class Chopstick {
	private boolean taken;

	public Chopstick() {
		taken = false;
	}

	public void take() {
		synchronized (this) {
			while (taken) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			taken = true;
		}
	}

	public void drop() {
		synchronized (this) {
			taken = false;
			this.notifyAll();
		}
	}
}

class Philosopher implements Runnable {

	private Chopstick left;
	private Chopstick right;
	private int id;
	private int ponderFactor;
	private Random random;

	public Philosopher(Chopstick left, Chopstick right, int id, int ponderFactor) {
		this.left = left;
		this.right = right;
		this.id = id;
		this.ponderFactor = ponderFactor;
		this.random = new Random();
	}

	public void pause(int ponderFactor) {
		try {
			Thread.sleep(random.nextInt(ponderFactor) * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			System.out.println(this + " is thinking.");
			pause(ponderFactor);
			left.take();
			right.take();
			System.out.println(this + " is eating.");
			pause(ponderFactor);
			right.drop();
			left.drop();
		}
	}

	@Override
	public String toString() {
		return "Philosopher " + id;
	}
}