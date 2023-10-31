package lab.assignment_1;

class ThreadInfo {
    int arrivalTime;
    int executionTime;
    int waitingTime;
    int runningTime;
    
    ThreadInfo(int arrivalTime, int executionTime) {
        this.arrivalTime = arrivalTime;
        this.executionTime = executionTime;
        this.waitingTime = 0;
        this.runningTime = 0;
    }
}

public class ThreadSchedulingSimulation {
    public static void main(String[] args) {
        ThreadInfo[] threads = {
            new ThreadInfo(0, 3),
            new ThreadInfo(1, 2),
            new ThreadInfo(3, 5),
            // ... Add more threads as needed
        };

        int currentTime = 0;
        for (ThreadInfo thread : threads) {
            if (thread.arrivalTime > currentTime) {
                currentTime = thread.arrivalTime;
            }
            thread.waitingTime = currentTime - thread.arrivalTime;
            thread.runningTime = thread.executionTime;
            currentTime += thread.executionTime;
        }

    }
}
