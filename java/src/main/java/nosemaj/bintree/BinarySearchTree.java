/*
 * Copyright 2017 nosemaj.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.nosemaj.bintree;

import java.util.ArrayList;
import java.util.List;

/**
 * A BinarySearchTree is a BinaryTree with the additional properties
 * that all values in a left subtree are less than the value in a parent
 * node, and all values in a right subtree are greater than or equal to
 * the value in a parent node.
 *
 * @param <T> The type of value being kept in the tree
 */
public class BinarySearchTree<T extends Comparable<T>>
        implements BinaryTree<T> {

    /**
     * The root of the tree, null when empty.
     */
    private Node<T> root;

    @Override
    public void add(final T value) {
        if (value == null) {
            return;
        } else if (empty()) {
            root = new Node<>(value);
        } else {
            add(root, value);
        }
    }

    /**
     * Adds a value to the subtree whose root is parent.
     * @param parent Parent node of a subtree, must not be null
     * @param value Value to add to subtree
     */
    private void add(final Node<T> parent, final T value) {
        int comparison = value.compareTo(parent.getValue());

        if (comparison < 0) {
            if (parent.hasLeft()) {
                add(parent.getLeft(), value);
            } else {
                parent.setLeft(new Node<>(value));
            }
        } else { // comparison >= 0
            if (parent.hasRight()) {
                add(parent.getRight(), value);
            } else {
                parent.setRight(new Node<>(value));
            }
        }
    }

    @Override
    public void remove(final T value) {
        root = remove(root, value);
    }

    /**
     * Removes all instances of a value, from a subtree whose root is
     * parent, returning a new parent (since it might have changed).
     * @param parent The root of the subtree
     * @param value The value to remove from the subtree
     * @return New parent of the subtree, possibly the same
     */
    private Node<T> remove(final Node<T> parent, final T value) {
        if (parent == null || value == null) {
            return parent;
        }

        // Cleanup our children before dealing with the current node
        parent.setLeft(remove(parent.getLeft(), value));
        parent.setRight(remove(parent.getRight(), value));

        if (!parent.getValue().equals(value)) {
            return parent;
        }

        // Okay, now we have to deal with our actual problem -- parent
        // contains the value to be removed.

        if (parent.isLeaf()) {
            // For a leaf, the new subtree parent is null (there is no
            // subtree anymore)
            return null;
        } else if (!parent.hasLeft()) {
            // If there is only one child, just treat it as the new root
            // of the subtree.
            return parent.getRight();
        } else if (!parent.hasRight()) {
            // Ditto
            return parent.getLeft();
        }

        /*
         * If parent has two children, we find the next successor node,
         * and swap values with it. Then, we just invoke remove again to
         * get rid of the bad value we put in the successor node. By
         * virtue of this, the parent node reference won't actually
         * change, so we'll still return it.
         */
        Node<T> min = minNode(parent.getRight());
        parent.setValue(min.getValue());
        min.setValue(value);
        parent.setRight(remove(parent.getRight(), value));

        return parent;
    }

    /**
     * Gets a node which stores the minimum value in a subtree.
     * @param parent A root of a subtree
     * @return A node which stores the minimum value in the subtree
     */
    private Node<T> minNode(final Node<T> parent) {
        Node<T> node = parent;

        while (node.hasLeft()) {
            node = node.getLeft();
        }

        return node;
    }

    @Override
    public boolean contains(final T value) {
        return count(value) != 0;
    }

    @Override
    public int count(final T value) {
        return count(root, value);
    }

    /**
     * Gets the count of values that exist in a subtree whose root is
     * parent.
     * @param parent The root of a subtree
     * @param value The value for which we want to count occurrences in
     *              the subtree
     * @return The count of occureences of the value within the subtree
     */
    private int count(final Node<T> parent, final T value) {
        if (parent == null || value == null) {
            return 0;
        }

        int count = count(parent.getLeft(), value)
            + count(parent.getRight(), value);

        if (parent.getValue().equals(value)) {
            count++;
        }

        return count;
    }

    @Override
    public int height() {
        return height(root);
    }

    /**
     * Gets the height of the subtree whose root is parent.
     * @param parent The root of a substree
     * @return The height of the subtree whose root is parent
     */
    private int height(final Node<T> parent) {
        if (parent == null) {
            return -1;
        } else if (parent.isLeaf()) {
            return 0;
        }

        int leftHeight = 1 + height(parent.getLeft());
        int rightHeight = 1 + height(parent.getRight());

        return Math.max(leftHeight, rightHeight);
    }

    @Override
    public int size() {
        return size(root);
    }

    /**
     * Gets the number of nodes in a subtree whose root is parent.
     * @param parent The root of a subtree
     * @return The number of nodes in the subtree, including parent
     */
    private int size(final Node<T> parent) {
        if (parent == null) {
            return 0;
        } else if (parent.isLeaf()) {
            return 1;
        }

        return 1 + size(parent.getLeft()) + size(parent.getRight());
    }

    @Override
    public boolean empty() {
        return root == null;
    }

    @Override
    public List<T> levelOrder() {
        List<T> values = new ArrayList<>();

        if (empty()) {
            return values;
        }

        List<Node<T>> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> current = queue.remove(0);
            values.add(current.getValue());

            if (current.hasLeft()) {
                queue.add(current.getLeft());
            }

            if (current.hasRight()) {
                queue.add(current.getRight());
            }
        }

        return values;
    }

    @Override
    public List<T> preOrder() {
        List<T> values = new ArrayList<>();
        preOrder(root, values);
        return values;
    }

    /**
     * Performs an pre-order traversal on the subtree whose root is
     * parent, adding seen values to the provided list.
     * @param parent Root of a substree
     * @param values Values seen during a traversal of a full tree
     */
    private void preOrder(final Node<T> parent, final List<T> values) {
        if (parent == null) {
            return;
        }

        values.add(parent.getValue());
        preOrder(parent.getLeft(), values);
        preOrder(parent.getRight(), values);
    }

    @Override
    public List<T> inOrder() {
        List<T> values = new ArrayList<>();
        inOrder(root, values);
        return values;
    }

    /**
     * Performs an in-order traversal of the subtree whose root is
     * parent, adding found values to the provided list of values.
     * @param parent The parent node of a substree
     * @param values The list of values seen so far in an in-order
     *               traversal of the larger tree
     */
    private void inOrder(final Node<T> parent, final List<T> values) {
        if (parent == null) {
            return;
        }

        inOrder(parent.getLeft(), values);
        values.add(parent.getValue());
        inOrder(parent.getRight(), values);
    }

    @Override
    public List<T> postOrder() {
        List<T> values = new ArrayList<>();
        postOrder(root, values);
        return values;
    }

    /**
     * Performs a post-order traversal on the substree whose root is
     * parent, adding found values to the provided list of values.
     * @param parent The parent of a subtree
     * @param values The list of values seen so far in a postorder
     * traversal of the larger tree
     */
    private void postOrder(final Node<T> parent, final List<T> values) {
        if (parent == null) {
            return;
        }

        postOrder(parent.getLeft(), values);
        postOrder(parent.getRight(), values);
        values.add(parent.getValue());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Tree:");

        for (T value : levelOrder()) {
            builder.append(" " + value);
        }

        return builder.toString();
    }
}

