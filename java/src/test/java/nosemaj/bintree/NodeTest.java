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

import java.util.Random;

/**
 * Tests the Node class.
 */
public final class NodeTest {

    /**
     * A random number generator used to construct arbitrary node
     * values.
     */
    private Random random;

    /**
     * Sets up dependencies needed for the test.
     */
    @Before
    public void setup() {
        random = new Random();
    }

    /**
     * The {@link Node(T)} constructor should result in a node
     * that has a value, and no children.
     */
    @Test
    public void shouldReturnValidLeafForOneArgConstructor() {
        float value = random.nextFloat();

        Node<Float> node = new Node<>(value);

        // Will also test some our syntactic fluff methods here
        Assert.assertEquals(value, (float) node.getValue(), 0);
        Assert.assertTrue(node.isLeaf());
        Assert.assertFalse(node.hasLeft());
        Assert.assertEquals(null, node.getLeft());
        Assert.assertFalse(node.hasRight());
        Assert.assertEquals(null, node.getRight());
    }

    /**
     * The {@link Node(T, Node<T>, Node<T>)}
     * constructor should result in a node with the specificed value and
     * children.
     */
    @Test
    public void shouldReturnValidNodeForFullArgsConstructor() {
        int value = random.nextInt();
        Node<Integer> left = new Node<>(random.nextInt());
        Node<Integer> right = new Node<>(random.nextInt());

        Node<Integer> node = new Node<>(value, left, right);

        // Will also test some our syntactic fluff methods here
        Assert.assertEquals(value, (int) node.getValue());
        Assert.assertFalse(node.isLeaf());
        Assert.assertTrue(node.hasLeft());
        Assert.assertEquals(left, node.getLeft());
        Assert.assertTrue(node.hasRight());
        Assert.assertEquals(right, node.getRight());
    }


    /**
     * Setting the value should change the value in the node object.
     */
    @Test
    public void shouldChangeValueWhenSetValueCalled() {

        Object originalValue = new Object();
        Object newValue = new Object();
        Node<Object> node = new Node<>(originalValue);

        node.setValue(newValue);

        Assert.assertEquals(newValue, node.getValue());
    }

    /**
     * Setting the left child should change the value on the node
     * object.
     */
    @Test
    public void shouldChangeChildWhenSetLeftCalled() {
        Node<Object> originalLeft = new Node<>(new Object());
        Node<Object> newLeft = new Node<>(new Object());
        Node<Object> parent = new Node<>(new Object(), originalLeft, null);

        parent.setLeft(newLeft);
        Assert.assertEquals(newLeft, parent.getLeft());
        Assert.assertNull(parent.getRight()); // Didn't change

    }

    /**
     * Setting the right child should change the value on the node
     * object.
     */
    @Test
    public void shouldChangeChildWhenSetRightCalled() {
        Node<Object> originalRight = new Node<>(new Object());
        Node<Object> newRight = new Node<>(new Object());
        Node<Object> parent = new Node<>(new Object(), null, originalRight);

        parent.setRight(newRight);
        Assert.assertEquals(newRight, parent.getRight());
        Assert.assertNull(parent.getLeft()); // Didn't change
    }

    /**
     * A node should be equal to itself.
     */
    @Test
    public void shouldReturnTrueWhenNodeEqualsSelf() {
        Node<Boolean> node = new Node<>(random.nextBoolean());

        Assert.assertEquals(node, node);
    }

    /**
     * A node must not be equal to another node if they have different
     * values.
     */
    @Test
    public void shouldReturnFalseWhenNodeEqualsDifferentValue() {
        Boolean value = random.nextBoolean();
        Node<Boolean> node = new Node<>(value);
        Node<Boolean> different = new Node<>(!value);

        Assert.assertNotEquals(node, different);
    }

    /**
     * A node should be equal to another node provided that the store
     * the same value, regardless of the children to which either
     * points.
     */
    @Test
    public void shouldReturnTrueWhenEqualsOnlyChildrenAreDifferent() {
        Long value = random.nextLong();
        Node<Long> one = new Node<>(random.nextLong());
        Node<Long> two = new Node<>(random.nextLong());
        Node<Long> three = new Node<>(random.nextLong());

        Node<Long> node = new Node<>(value, one, two);
        Node<Long> other = new Node<>(value, two, three);

        Assert.assertEquals(node, other);
    }

    /**
     * A node is never equal to an object that is not a node.
     */
    @Test
    public void shouldReturnFalseEqualsDifferentObjectClass() {
        double value = random.nextDouble();
        Node<Double> node = new Node<>(value);
        Double other = Double.valueOf(value);

        Assert.assertNotEquals(node, other);
    }

    /**
     * A node is never equal to a node of a different template type.
     */
    @Test
    public void shouldReturnFalseEqualsDifferentTemplate() {
        int value = random.nextInt();
        Node<Integer> node = new Node<>(value);
        Node<Long> other = new Node<>((long) value);

        Assert.assertNotEquals(node, other);
    }

    /**
     * An node that exists is never equal to a node that does not exist.
     */
    @Test
    public void shouldReturnFalseEqualsNull() {
        Node<Object> node = new Node<>(new Object());
        Assert.assertNotEquals(node, null);
    }

    /**
     * Nodes with different values should have different hash codes.
     */
    @Test
    public void shouldReturnDistinctHashesForDistinctNodes() {

        Node<Object> one = new Node<>(new Object());
        Node<Object> two = new Node<>(new Object());

        Assert.assertNotEquals(one.hashCode(), two.hashCode());
    }

    /**
     * The syntactic fluff methods to check if a node has left, right,
     * or no children, should function propertly for a node that has
     * unbalanced children (only on one side).
     */
    @Test
    public void shouldDiscernUnbalancedChildrenAppropriately() {

        Node<Object> left = new Node<>(new Object());
        Node<Object> parent = new Node<>(new Object(), left, null);

        Assert.assertTrue(parent.hasLeft());
        Assert.assertFalse(parent.hasRight());
        Assert.assertFalse(parent.isLeaf());
    }
}
