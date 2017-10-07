package structures;

import static org.junit.Assert.*;

import org.junit.Test;

import structures.Queue;
import structures.UnboundedQueueInterface;

public class StudentTests {

	@Test
	public void testReverseSingleInput() throws Exception {
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(1);
		UnboundedQueueInterface<Integer> r = new Queue<Integer>(q);
		r = q.reversed();
		assertEquals(r.dequeue(),q.dequeue());
	}
	
	@Test
	public void testDoubleReverse() throws Exception{
		Queue<Integer> q = new Queue<Integer>();
		UnboundedQueueInterface<Integer> r;
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		r = q.reversed();
		UnboundedQueueInterface<Integer> t = r.reversed();
		assertEquals(q.dequeue(),t.dequeue());
	}
	@Test
	public void testReverseVals() throws Exception{
		Queue<Integer> q = new Queue<Integer>();
		UnboundedQueueInterface<Integer> r;
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		r = q.reversed();
		assertEquals(3, (int)r.dequeue());
		assertEquals(2,(int)r.dequeue());
	}
	
	@Test
	public void testConstructorEmptyUnaliased() throws Exception {
		Queue<Integer> temp = new Queue<Integer>();
		Queue<Integer> result = new Queue<Integer>(temp);
		assertTrue(result.isEmpty());
		temp.enqueue(1);
		assertTrue(result.isEmpty());
	}
}

