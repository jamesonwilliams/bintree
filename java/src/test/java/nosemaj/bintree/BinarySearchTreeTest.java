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
        Assert.assertEquals(0, tree.size());
    }

    /**
     * Adding a value to an empty tree makes it become not empty.
     */
    @Test
    public void shouldAddValueToEmpty() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        Integer value = Integer.valueOf(random.nextInt());

        tree.add(value);

        Assert.assertFalse(tree.empty());
        Assert.assertEquals(1, tree.size());
        Assert.assertTrue(tree.contains(value));
        Assert.assertEquals(1, tree.count(value));
    }

    /**
     * Adding incremental values to a tree causes the size of the tree
     * to increment correspondingly.
     */
    @Test
    public void shouldAddValueToNonEmptyTree() {
        BinaryTree<Boolean> tree = new BinarySearchTree<>();
        boolean value = random.nextBoolean();
        Boolean firstVal = Boolean.valueOf(value);
        Boolean secondVal = Boolean.valueOf(!value);

        tree.add(firstVal);

        Assert.assertEquals(1, tree.size());
        Assert.assertTrue(tree.contains(firstVal));
        Assert.assertFalse(tree.contains(secondVal));
        Assert.assertEquals(1, tree.count(firstVal));

        tree.add(secondVal);

        Assert.assertTrue(tree.contains(firstVal));
        Assert.assertEquals(1, tree.count(firstVal));
        Assert.assertTrue(tree.contains(secondVal));
        Assert.assertEquals(1, tree.count(secondVal));
        Assert.assertEquals(2, tree.size());
    }

    /**
     * Should not be able to add nulls to the tree.
     */
    @Test
    public void shouldNotAddNullValuesToTree() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();

        tree.add(null);

        Assert.assertTrue(tree.empty());
        Assert.assertEquals(0, tree.size());
        Assert.assertFalse(tree.contains(null));
        Assert.assertEquals(0, tree.count(null));
    }

    /**
     * Make sure height works for the happy path.
     */
    @Test
    public void shouldReturnCorrectHeight() {
        BinaryTree<Float> tree = new BinarySearchTree<>();
        Float value = random.nextFloat();
        tree.add(value);
        tree.add(value + 1);
        tree.add(value + 1);
        tree.add(value + 2);

        int height = tree.height();

        Assert.assertEquals(3, height);
    }

    /**
     * An empty tree has height -1.
     */
    @Test
    public void shouldReturnMinusOneForEmptyTreeHeight() {
        BinaryTree<Boolean> tree = new BinarySearchTree<>();

        int height = tree.height();

        Assert.assertTrue(tree.empty());
        Assert.assertEquals(-1, height);
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
        Integer value = random.nextInt();
        tree.add(value);
        tree.add(value - 1);
        tree.add(value + 1);

        List<Integer> values = tree.inOrder();

        assertEquals(value - 1, values.get(0));
        assertEquals(value, values.get(1));
        assertEquals(value + 1, values.get(2));
    }

    /**
     * A pre-order traversal should return values in pre-order.
     */
    @Test
    public void shouldReturnPreOrderValueForPreOrderTraversal() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        Integer value = random.nextInt();
        tree.add(value);
        tree.add(value - 1);
        tree.add(value + 1);

        List<Integer> values = tree.preOrder();

        assertEquals(value, values.get(0));
        assertEquals(value - 1, values.get(1));
        assertEquals(value + 1, values.get(2));
    }

    /**
     * A post-order traversal should return values in post-order.
     */
    @Test
    public void shouldReturnPostOrderValuesForPostOrderTraversal() {

        BinaryTree<Integer> tree = new BinarySearchTree<>();
        Integer value = random.nextInt();
        tree.add(value);
        tree.add(value - 1);
        tree.add(value + 1);

        List<Integer> values = tree.postOrder();

        assertEquals(value - 1, values.get(0));
        assertEquals(value + 1, values.get(1));
        assertEquals(value, values.get(2));
    }

    /**
     * A level-order traversal should return values in level-order.
     */
    @Test
    public void shouldReturnLevelOrderValuesForLevelOrderTraversal() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        Integer value = random.nextInt();
        tree.add(value);
        tree.add(value - 1);
        tree.add(value + 1);

        List<Integer> values = tree.levelOrder();

        assertEquals(value, values.get(0));
        assertEquals(value - 1, values.get(1));
        assertEquals(value + 1, values.get(2));
    }

    /**
     * Removing a value should remove all instances of it, but not
     * effect unrelated values.
     */
    @Test
    public void shouldRemoveAllDuplicateValues() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        Integer value = random.nextInt();
        tree.add(value);
        tree.add(value + 1);
        tree.add(value);
        Assert.assertEquals(3, tree.size());
        Assert.assertEquals(2, tree.count(value));
        Assert.assertEquals(1, tree.count(value + 1));

        tree.remove(value);
        Assert.assertEquals(1, tree.size());
        Assert.assertFalse(tree.contains(value));
        Assert.assertEquals(0, tree.count(value));
        Assert.assertTrue(tree.contains(value + 1));
        Assert.assertEquals(1, tree.count(value + 1));
    }

    /**
     * Removing a value from a tree that is just a single node should
     * result in an empty tree.
     */
    @Test
    public void shouldRemoveRootLeaf() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        Integer value = random.nextInt();
        tree.add(value);
        Assert.assertEquals(1, tree.count(value));

        tree.remove(value);

        Assert.assertEquals(0, tree.count(value));
        Assert.assertTrue(tree.empty());
    }

    /**
     * Try removing some various nodes in a multi-level tree.
     */
    @Test
    public void shouldRemoveValueInFullTree() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        Integer value = random.nextInt();
        tree.add(value);
        tree.add(value - 5);
        tree.add(value + 5);
        tree.add(value - 7);
        tree.add(value - 3);
        tree.add(value + 3);
        tree.add(value + 7);
        tree.add(value);

        // Remove an interior node that has two children and has a
        // parent
        int originalSize = tree.size();
        tree.remove(value - 5);
        Assert.assertFalse(tree.contains(value - 5));
        Assert.assertEquals(originalSize - 1, tree.size());

        // Remove both the root node, as well the second instance of 10
        // (that was added last.)
        tree.remove(value);
        Assert.assertFalse(tree.contains(value));
        Assert.assertEquals(originalSize - 3, tree.size());
    }

    /**
     * Trying to remove null from the tree should have no effect (since
     * nulls cannot be stored).
     */
    @Test
    public void shouldNotEffectTreeWhenRemoveNull() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        Integer value = Integer.valueOf(random.nextInt());
        tree.add(value);
        tree.remove(null);

        Assert.assertEquals(1, tree.size());
        Assert.assertTrue(tree.contains(value));
        Assert.assertEquals(1, tree.count(value));
    }

    /**
     * Trying to remove a value that doesn't exist in the tree should
     * have no effect.
     */
    @Test
    public void shouldNotEffectTreeRemoveOnEmptyTree() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        Assert.assertTrue(tree.empty());

        Integer value = random.nextInt();
        tree.remove(value);

        Assert.assertTrue(tree.empty());
        Assert.assertEquals(0, tree.count(value));
    }

    /**
     * The toString implementation should make some sensible reference
     * to the values being stored in the tree.
     */
    @Test
    public void shouldReturnSensibleToString() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        Integer value = random.nextInt();
        tree.add(value);

        String string = tree.toString();
        Assert.assertTrue(string.contains(value.toString()));
    }

    /**
     * A tree is equal to itself.
     */
    @Test
    public void shouldReturnTrueForTreeEqualsSelf() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        Integer value = random.nextInt();
        tree.add(value);
        Assert.assertEquals(tree, tree);
    }

    /**
     * Equals should return false when checked against a tree containing
     * different values.
     */
    @Test
    public void shouldReturnFalseForTreeEqualsDifferentTree() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        Integer value = random.nextInt();
        tree.add(value);

        BinaryTree<Integer> otherTree = new BinarySearchTree<>();
        Integer otherValue = value - 1;
        tree.add(otherValue);

        Assert.assertNotEquals(tree, otherTree);
    }

    /**
     * Make sure hashcode generates some non-zero number for a non-empty
     * tree.
     */
    @Test
    public void shouldReturnValidHashCodeForNonEmptyTree() {
        BinaryTree<Integer> tree = new BinarySearchTree<>();
        tree.add(random.nextInt());
        tree.add(random.nextInt());

        Assert.assertNotEquals(0, tree.hashCode());
    }

    /**
     * Checks that an actual object is equal to an expected object.
     * @param expected The expected object
     * @param actual The actaul object
     */
    private void assertEquals(final Object expected, final Object actual) {

        if (expected == null) {
            Assert.assertNull(actual);
            return;
        }

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected, actual);
    }
}
