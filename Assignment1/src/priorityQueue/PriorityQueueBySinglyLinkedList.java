package priorityQueue;
import linkedList.SingleLink;
import linkedList.SingleTaskNode;
import queue.Queue;
import task.Task;

public class PriorityQueueBySinglyLinkedList extends Queue {

	private SingleLink<Task> queue = new SingleLink<Task>();
	
	/**
	 * Adds an element to the rear of the queue.
	 * @param task: the element that is to be added to the queue
	 */
	@Override
	public void enqueue(Task task) {
		// TODO Auto-generated method stub
		queue.insertFromTail(task);
	}
	
	/**
	 * Removes an element with the highest priority.
	 */
	@Override
	public Task dequeue() {
		if (isEmpty()) {
            throw new RuntimeException("This queue is empty.");
        }
        // Find the index of the element with the highest priority.
		int index = 1;
        for (int i = 1; i <queue.getLength(); i++) {
            if( queue.getNode(i).getData().getImportance()>queue.getNode(index).getData().getImportance())
            	index=i;
        }
        Task task = queue.getNode(index).getData();
        // Move all the elements forward which follow the removed element.
        queue.remove(index);
        return task;
	}
	
	public Task dequeue_opti() {
		if (isEmpty()) {
            throw new RuntimeException("This queue is empty.");
        }
		SingleTaskNode<Task> head = queue.getHead();
		SingleTaskNode<Task> dummy = new SingleTaskNode<Task>(new Task(0), head);		
		SingleTaskNode<Task> prev = dummy;
		SingleTaskNode<Task> curr = null;
		SingleTaskNode<Task> maxNode = null;
		SingleTaskNode<Task> maxPrev = null;
		int maxValue = Integer.MIN_VALUE;
		while ((curr = prev.getNext()) != null) {
			if (curr.getData().getImportance() > maxValue) {
				maxValue = curr.getData().getImportance();
				maxNode = curr;
				maxPrev = prev;			
			}
			prev = curr;
		}
		maxPrev.setNext(maxNode.getNext());
		queue.setHead(dummy.getNext());
		queue.setLength(queue.getLength() - 1);
        return maxNode.getData();
	}
	
	/**
	 * Gets the element with the highest priority.
	 */
	@Override
	public Task peek() {
        if (isEmpty()) {
            throw new RuntimeException("This queue is empty");
        }
        int index = 1;
        for (int i = 1; i <queue.getLength(); i++) {
            if( queue.getNode(i).getData().getImportance()>queue.getNode(index).getData().getImportance())
            	index=i;
        }
        return queue.getNode(index).getData();
    }

	/**
	 * Checks whether the queue is empty.
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queue.getLength()==0;
	}

	/**
	 * Gets the number of the elements in the queue.
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return queue.getLength();
	}

	/**
	 * Clears the queue.
	 */
	@Override
	public void empty() {
		// TODO Auto-generated method stub
        queue.clear();
	}

	/**
	 * Checks whether the queue is full.
	 */
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return queue.isFull();
	}

}
