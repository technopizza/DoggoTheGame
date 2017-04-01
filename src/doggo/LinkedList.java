/**
 * *****************************************************************************
 * Filename: LinkedList.java
 * 
* Author: Conklin, Jason Date: December 16, 2016
 * 
* Description: class used to create and manipulate a LinkList object
 * 
* Input: Node, LinkedList
 * 
* Output: Console
 * *****************************************************************************
 */
package doggo;

public class LinkedList<T> {

    private Node<T> head;
    private Node<T> currentNode;

    public LinkedList(Node head) {
        this.head = head;
        currentNode = this.head;
    }

    /**
     * ******************************************************************************
     * Method: printContents
     *
     * Description: iterates through each node in the LinkedList, printing out
     * its contents
     *
     * Parameters: none
     *
     * Pre-Conditions: none
     *
     * Post-Conditions: none
     * *****************************************************************************
     */
    /*
     void printContents() {
     Node currentNode;
     if (!(head == null)) {
     currentNode = head;
     System.out.println("Linked List:");
     while (!(currentNode == null)) {
     System.out.println("    " + currentNode.getName());
     System.out.println("        " + currentNode.getSpecies());
     System.out.println("        " + currentNode.getAbility());
     System.out.println("        " + currentNode.getStrengthRating());
     currentNode = currentNode.getPointer();
     }
     } else {
     System.out.println("\nLinked List is empty!\n");
     }

     }
     */
    /**
     * ******************************************************************************
     * Method: addNodeFront
     *
     * Description: adds a new Node to the list by setting head equal to the new
     * node, and having the new node point to the object that already was the
     * head
     *
     * Parameters: Node node - the node to be added
     *
     * Pre-Conditions: none
     *
     * Post-Conditions: head will have the value of the new node, and the old
     * head will be the second item in the
     * *****************************************************************************
     */
    void addNodeFront(Node node) {
        node.setNextNode(head);
        head = node;
    }

    void addNodeBack(Node node) {

        Node tmpNode = head;
        if (!(head == null)) {
            tmpNode = head;

            while (tmpNode.getNextNode() != null) {
                tmpNode = tmpNode.getNextNode();
            }
            tmpNode.setNextNode(node);

//            if (!(tmpNode.getNextNode() == null)) {
//                while (!(tmpNode.getNextNode().getNextNode() == null)) {
//                    tmpNode = tmpNode.getNextNode();
//                }
//                tmpNode.setNextNode(node);
//            } else {
//                head = node;
//            }
        } else {
            head = node;
        }

    }

    /**
     * ******************************************************************************
     * Method: removeNodeFront
     *
     * Description: removes Node to the list by setting head equal to the head's
     * pointer
     *
     * Parameters: None
     *
     * Pre-Conditions: none
     *
     * Post-Conditions: head will have the value of head's pointer and the old
     * head gone
     * *****************************************************************************
     */
    void removeNodeFront() {
        if (!(head == null)) {
            head = head.getNextNode();
        }/* else {
         System.out.println("\nLinked List is empty!\n");
         }*/

    }

    void removeNodeWithData(Object data) {
        Node tmpNode;

        if(head != null && head.getNextNode() != null){
            tmpNode = head;
            
            while(tmpNode.getNextNode() != null){
                if(tmpNode.getNextNode().getData().equals(data)){
                    
                    if(tmpNode.getNextNode().getNextNode() != null){
                        tmpNode.setNextNode(tmpNode.getNextNode().getNextNode());
                    }else{
                        tmpNode.setNextNode(null);
                    }
                    
                    return;
                }
                tmpNode = tmpNode.getNextNode();
            }
            
        }
        else{
            head = null;
        }
        
        
    }

    /**
     * ******************************************************************************
     * Method: removeNodeBack
     *
     * Description: iterates through list to find second-to-last node, pointing
     * it to null
     *
     * Parameters: None
     *
     * Pre-Conditions: none
     *
     * Post-Conditions: last node of Linked list removed
     * *****************************************************************************
     */
    void removeNodeBack() {
        Node tmpNode;
        if (!(head == null)) {
            tmpNode = head;
            if (!(tmpNode.getNextNode() == null)) {
                while (!(tmpNode.getNextNode().getNextNode() == null)) {
                    tmpNode = tmpNode.getNextNode();
                }
                tmpNode.setNextNode(null);
            } else {
                head = null;
            }

        }/* else {
         System.out.println("\nLinked List is empty!\n");
         }*/

    }

    void loopLinkedList() {
        Node tmpNode;
        if (!(head == null)) {
            tmpNode = head;
            if (!(tmpNode.getNextNode() == null)) {
                while (!(tmpNode.getNextNode() == null)) {
                    tmpNode = tmpNode.getNextNode();
                }
                tmpNode.setNextNode(head);
            } else {
                tmpNode.setNextNode(head);
            }

        }/* else {
         System.out.println("\nLinked List is empty!\n");
         }*/

    }

    public Node getHead() {
        return head;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public Node nextNode() {
        currentNode = currentNode.getNextNode();
        return currentNode;
    }

    public void reset() {
        currentNode = head;

    }
}
