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

import java.util.Objects;

/**
 * A node in a binary tree contains a value, as well as pointers to left
 * and right child nodes, below it in the binary tree.
 *
 * @param <T> The type of value stored in the node
 */
public class Node<T> {

    /**
     * The left child.
     */
    private Node<T> left;

    /**
     * The right child.
     */
    private Node<T> right;

    /**
     * The stored value.
     */
    private T value;

    /**
     * Constructs a new node with no children.
     * @param value The value to store in the node
     */
    public Node(final T value) {
        this(value, null, null);
    }

    /**
     * Constructs a new node.
     * @param value The value to store in the node
     * @param left The left child of the node, null if not present
     * @param right The right child of the node, null if not present
     */
    public Node(final T value, final Node<T> left, final Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
     * Gets the value stored in the node.
     * @return The value stored in the node
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the value stored in the node.
     * @param value The value to store in the node
     */
    public void setValue(final T value) {
        this.value = value;
    }

    /**
     * Determines if the node has a left child.
     * @return true if the node has a left child
     */
    public boolean hasLeft() {
        return left != null;
    }

    /**
     * Gets the left child of the node.
     * @return Left child of the node, null if not present
     */
    public Node<T> getLeft() {
        return left;
    }

    /**
     * Sets the left child of the node.
     * @param left The left child of the node, null if not present
     */
    public void setLeft(final Node<T> left) {
        this.left = left;
    }

    /**
     * Determines if the node has a right child.
     * @return true if the node has a right child, false otherwise
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     * Gets the right child of the node.
     * @return Right child of the node, null if there is none
     */
    public Node<T> getRight() {
        return right;
    }

    /**
     * Sets the right child of the node.
     * @param right The right child of the node, null if not present
     */
    public void setRight(final Node<T> right) {
        this.right = right;
    }

    /**
     * Determines if the node is a leaf node. A leaf node is a node
     * which has no children.
     * @return true if the node is a leaf node; false, otherwise
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left, right);
    }

    @Override
    public boolean equals(final Object thatObject) {
        if (thatObject == null) {
            return false;
        }

        if (getClass() != thatObject.getClass()) {
            return false;
        }

        Node<T> thatNode = (Node<T>) thatObject;
        if (!Objects.equals(this.value, thatNode.value)) {
            return false;
        }

        return true;
    }
}

