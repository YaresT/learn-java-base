package lesson30;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class MedianIntegerStream {
    private final Queue<Integer> minHeap;

    private final Queue<Integer> maxHeap;

    public MedianIntegerStream() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void insert(final int num) {
        if (minHeap.size() == maxHeap.size()) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
    }

    public double getMedian() {
        int median = 0;

        if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
            if (minHeap.size() > maxHeap.size()) {
                median = minHeap.peek();
            } else {
                median = (minHeap.peek() + maxHeap.peek()) / 2;
            }
        }

        return median;
    }

    public void removeMedian() {
        if (minHeap.size() > maxHeap.size()) {
            minHeap.poll();
        } else {
            maxHeap.poll();
        }
    }
}
