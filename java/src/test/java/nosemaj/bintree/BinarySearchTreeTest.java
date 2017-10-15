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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

/**
 * Tests the Binary Search Tree implementation.
 */
public final class BinarySearchTreeTest {

    /**
     * A random number generator to fill in arbitrary state fields that
     * we can check, after exercising various class behaviours.
     */
    private Random random;

    /**
     * Sets up test dependencies.
     */
    @Before
    public void setup() {
        random = new Random();
    }

    /**
     * A new tree should be empty by default.
     */
    @Test
    public void shouldConstructEmptyTree() {
        BinaryTree<Float> tree = new BinarySearchTree<Float>();
        Assert.assertTrue(tree.empty());
    }

    /**
     * Adding a value to an empty tree makes it become not empty.
     */
    @Test
    public void shouldAddValueToEmpty() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();

        tree.add(Integer.valueOf(random.nextInt()));
        Assert.assertFalse(tree.empty());
    }

    /**
     * Adding incremental values to a tree causes the size of the tree
     * to increment correspondingly.
     */
    @Test
    public void shouldAddValueToNonEmptyTree() {
        BinaryTree<Boolean> tree = new BinarySearchTree<>();
        tree.add(Boolean.valueOf(random.nextBoolean()));
        Assert.assertEquals(1, tree.size());

        tree.add(Boolean.valueOf(random.nextBoolean()));
        Assert.assertEquals(2, tree.size());
    }

    /**
     * Make sure height works for the happy path.
     */
    @Test
    public void shouldReturnCorrectHeight() {
        BinaryTree<Float> tree = new BinarySearchTree<>();
        tree.add(4f);
        tree.add(5f);
        tree.add(5f);
        tree.add(6f);

        Assert.assertEquals(3, tree.height());
    }

    /**
     * An empty tree has height -1.
     */
    @Test
    public void shouldReturnMinusOneForEmptyTreeHeight() {
        BinaryTree<Boolean> tree = new BinarySearchTree<>();
        Assert.assertTrue(tree.empty());
        Assert.assertEquals(-1, tree.height());
    }

    /**
     * A tree with one value has height 0.
     */
    @Test
    public void shouldReturnZeroForSingleNodeTreeHeight() {
        BinaryTree<Double> tree = new BinarySearchTree<>();
        tree.add(random.nextGaussian());

        Assert.assertEquals(1, tree.size());
        Assert.assertEquals(0, tree.height());
    }

    /**
     * An in-order traversal should return an increasing sequence of
     * values.
     */
    @Test
    public void shouldReturnIncreasingValueForInOrderTraversal() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(4);
        tree.add(6);

        List<Integer> values = tree.inOrder();

        Assert.assertEquals(4, values.get(0).intValue());
        Assert.assertEquals(5, values.get(1).intValue());
        Assert.assertEquals(6, values.get(2).intValue());
    }

    /**
     * A pre-order traversal should return values in pre-order.
     */
    @Test
    public void shouldReturnPreOrderValueForPreOrderTraversal() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(4);
        tree.add(6);

        List<Integer> values = tree.preOrder();

        Assert.assertEquals(5, values.get(0).intValue());
        Assert.assertEquals(4, values.get(1).intValue());
        Assert.assertEquals(6, values.get(2).intValue());
    }

    /**
     * A post-order traversal should return values in post-order.
     */
    @Test
    public void shouldReturnPostOrderValuesForPostOrderTraversal() {

        BinaryTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(4);
        tree.add(6);

        List<Integer> values = tree.postOrder();

        Assert.assertEquals(4, values.get(0).intValue());
        Assert.assertEquals(6, values.get(1).intValue());
        Assert.assertEquals(5, values.get(2).intValue());
    }

    /**
     * A level-order traversal should return values in level-order.
     */
    @Test
    public void shouldReturnLevelOrderValuesForLevelOrderTraversal() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(4);
        tree.add(6);

        List<Integer> values = tree.levelOrder();

        Assert.assertEquals(5, values.get(0).intValue());
        Assert.assertEquals(4, values.get(1).intValue());
        Assert.assertEquals(6, values.get(2).intValue());
    }
}
