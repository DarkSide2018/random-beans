/*
 * The MIT License
 *
 *    Copyright (c) 2016, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *    Permission is hereby granted, free of charge, to any person obtaining a copy
 *    of this software and associated documentation files (the "Software"), to deal
 *    in the Software without restriction, including without limitation the rights
 *    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *    copies of the Software, and to permit persons to whom the Software is
 *    furnished to do so, subject to the following conditions:
 *
 *    The above copyright notice and this permission notice shall be included in
 *    all copies or substantial portions of the Software.
 *
 *    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *    THE SOFTWARE.
 */

package io.github.benas.randombeans;

import io.github.benas.randombeans.api.Populator;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static io.github.benas.randombeans.PopulatorBuilder.aNewPopulatorBuilder;
import static org.assertj.core.api.Assertions.assertThat;

public class CollectionPopulatorTest {

    private CollectionPopulator collectionPopulator;

    @Before
    public void setUp() throws Exception {
        Populator populator = aNewPopulatorBuilder().build();
        ObjectFactory objectFactory = new ObjectFactory();
        collectionPopulator = new CollectionPopulator(populator, objectFactory);
    }

    @Test
    public void rawInterfaceCollectionTypesMustBeGeneratedEmpty() throws Exception {
        // Given
        Field field = Foo.class.getDeclaredField("rawInterfaceList");

        // When
        Collection<?> collection = collectionPopulator.getRandomCollection(field);

        // Then
        assertThat(collection).isEmpty();
    }

    @Test
    public void rawConcreteCollectionTypesMustBeGeneratedEmpty() throws Exception {
        // Given
        Field field = Foo.class.getDeclaredField("rawConcreteList");

        // When
        Collection<?> collection = collectionPopulator.getRandomCollection(field);

        // Then
        assertThat(collection).isEmpty();
    }

    @Test
    public void typedInterfaceCollectionTypesMustBePopulated() throws Exception {
        // Given
        Field field = Foo.class.getDeclaredField("typedInterfaceList");

        // When
        Collection<?> collection = collectionPopulator.getRandomCollection(field);

        // Then
        assertThat(collection).isNotEmpty();
    }

    @Test
    public void typedConcreteCollectionTypesMustBePopulated() throws Exception {
        // Given
        Field field = Foo.class.getDeclaredField("typedConcreteList");

        // When
        Collection<?> collection = collectionPopulator.getRandomCollection(field);

        // Then
        assertThat(collection).isNotEmpty();
    }

    class Foo {
        private List rawInterfaceList;
        private List<String> typedInterfaceList;
        private ArrayList rawConcreteList;
        private ArrayList<String> typedConcreteList;

        public List getRawInterfaceList() { return rawInterfaceList; }
        public void setRawInterfaceList(List rawInterfaceList) { this.rawInterfaceList = rawInterfaceList; }
        public List<String> getTypedInterfaceList() { return typedInterfaceList; }
        public void setTypedInterfaceList(List<String> typedInterfaceList) { this.typedInterfaceList = typedInterfaceList; }
        public ArrayList getRawConcreteList() { return rawConcreteList; }
        public void setRawConcreteList(ArrayList rawConcreteList) { this.rawConcreteList = rawConcreteList; }
        public ArrayList<String> getTypedConcreteList() { return typedConcreteList; }
        public void setTypedConcreteList(ArrayList<String> typedConcreteList) { this.typedConcreteList = typedConcreteList; }
    }
}