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

import java.util.List;

/**
 * A binary tree is an assembly of zero or more nodes, where each node
 * stores a value and may have a child to its left and right.
 *
 * @param <T> The type of value being stored in the tree
 */
public interface BinaryTree<T> {

    /**
     * Adds a value to the tree.
     * @param value The value to add to the tree
     */
    void add(T value);

    /**
     * Removes a value from the tree.
     * @param value The value to remove from the tree
     */
    void remove(T value);

    /**
     * Gets the height of the tree.
     * @return The height of the tree
     */
    int height();

    /**
     * Gets the size of the tree.
     * @return The size of the tree
     */
    int size();

    /**
     * Determines if the tree is empty or not.
     * @return True if the tree is empty; false, otherwise
     */
    boolean empty();

    /**
     * Traverse the tree in pre-order. The current node is added to the
     * list before left and right subtree are visited.
     * @return List of values in the tree, sorted in pre-order
     */
    List<T> preOrder();

    /**
     * Traverses the tree in post-order. The left and right subtress are
     * visited before the current node is added to the list.
     * @return List of values in the tree, sorted in post-order
     */
    List<T> postOrder();

    /**
     * Traverses the tree in natural order. The left subtree is
     * processed before the current node, followed by the right subtree.
     * @return List of values in the tree, sorted in natural order
     */
    List<T> inOrder();

    /**
     * Traverses the tree in level order. The order of nodes visited is
     * left-to-right accross a level of the tree.
     * @return List of values in the tree, sorted in level order
     */
    List<T> levelOrder();
}
