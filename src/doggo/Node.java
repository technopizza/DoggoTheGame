/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doggo;

/**
 *
 * @author Jason
 * @param <T>
 */
public class Node<T> {
    private T data;
    private Node nextNode;

    public Node(T data) {
        this.data = data;
    }
    

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    
    
    
}
