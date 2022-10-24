# Programming Abstractions – Homework II
*Computer Science, Stony Brook University*

In this assignment, you will be using Java (make sure it is JDK 1.8 compliant) for programming. Moreover,
this assignment requires you to invest quite a bit into thinking about abstraction before you start coding. It
is based on a mathematical structure called **group**. Before we get to the code, let us define this concept:
A nonempty set of elements G forms a **group** if in G there is a defined binary operation (which we will
denote by · in this document), such that
1. x, y ∈ G implies that x · y ∈ G. This property is called closure, and the set of elements is said to be
closed under the operation.
2. a, b, c ∈ G implies that a · (b · c) = (a · b) · c. In other words, the binary operation is associative.
3. There exists an element e ∈ G such that a · e = e · a = a for all elements a ∈ G. This special element e
is called the identity element of the group.
4. For every a ∈ G, there exists an element b such that a · b = b · a = e. That is, every element has an
inverse. Often, we simply denote it by a
−1
.

Much like programming, mathematics also relies on abstraction. Groups have become fundamentally important in modern mathematics because they distill the basic structural rules found in almost every important
mathematical structure. Some are very obvious, such as the set of all integers with addition as the binary operation. Note that the same set is NOT a group with multiplication as the operation (no inverse)! However,
as soon as we consider a bigger set, namely, the set of all real numbers, even with multiplication we have a
valid group. These examples serve to show that you should not simply think about the set of elements, but
instead, carefully consider the binary operation together with the set. It is also important to note that the
binary operation may not always be commutative. That is, it is not always the case that a · b = b · a.
For a basic understanding of implementing simple groups, there is some Java code already given to you. The
most important is the interface called Group. It is extensively documented, and students are expected to
pay attention to the details provided there. Next, there is an implementation of the most obvious group we
can think of: the group of all integers under addition. This is provided to you as ZPlus.

(*The name may seem strange, but Z is the standard mathematical symbol to represent the set of integers. And since addition
is the binary operation for this group, I decided to call the class ZPlus.*)

## Finite groups

Based on everything you have seen up to this point, you may think that groups are just a fancy way of
stating the basic properties of numbers. But that is not at all true! To start with, a group need not be
infinite. In fact, you will now be implementing a few finite groups

## Non-commutative groups


As noted earlier, the binary operation of a group need not be commutative. That is, a · b is not always equal
to b · a. This may not be intuitive if you only think of numeric operations. But they make a lot of sense
when we enter the world of geometry. In fact, one of the biggest applications of group theory is in fields
like chemistry and physics, where the structural symmetry of molecules and particles is studied using this
mathematical concept. So much so, that many consider the study of groups to be the “science of symmetry”.
For this assignment, we will look at two very simple examples – an equilateral triangle and a square – and
their symmetries.

**Figure 1**: The eight symmetries of a square: the identity operation that leaves everything as it is, three
rotation operations (around its center by 90◦
, 180◦
, and 270◦
), two reflections around the horizontal and
vertical lines, and two reflections around the two diagonals.

![Figure1](Figure1.png)

But first, another definition: two shapes are said to be congruent, if they have the same shape and size.
Formally, two shapes are congruent if one can be changed into the other by using a combination of:
- rotations (around a fixed point),
- reflections (around a line that serves as the axis of the reflection), and/or
- translations (a transformation that moves every point in the same direction by the same distance).

Clearly, any shape in the 2-dimensional x-y plane is congruent to itself. Some shapes, however, are congruent
to themselves in more than one way! Any such “extra” congruence is called a symmetry. A square has
eight symmetries, as shown in Fig. 1. Similarly, an equilateral triangle has six symmetries (three rotations
around its center by 0◦
, 120◦
, and 240◦
, and three reflections around the three perpendicular bisectors).
With this background, we are now ready to dive into some actual programming

### Task 1 ###
Let G be the set {±1}, under the standard multiplication of real numbers. Your first task is to implement (20)
this group in Java, with the name `FiniteGroupOfOrderTwo`.
When thinking about implementing this, note that `Group` is a parameterized interface. In the implemented example, ZPlus, the parameter was obvious, because we already know the data type for
“integers” (`Integer`, of course). But here, the valid data that forms the set of elements, consists of only
two values. So think about what should be the data type of the generic parameter. In your implementation, this parameter class must be named `PlusOrMinusOne`. You should also ensure that the following
driver method (given to you in the `ArithmeticTest` class) works with your code:

```
public static void main(String... args) {
    FiniteGroupOfOrderTwo g = new FiniteGroupOfOrderTwo();
    PlusOrMinusOne[] values = PlusOrMinusOne.values();
    System.out.printf("g.identity() = %s%n", g.identity());
    for (PlusOrMinusOne u : values) {
         for (PlusOrMinusOne v : values) {
            PlusOrMinusOne e = g.binaryOperation(u, v);
            System.out.printf("%s * %s = %s%n", u.toString(), v.toString(), e.toString());
            System.out.printf("inverseOf(%s) = %s%n", e.toString(), g.inverseOf(e).toString());
        }
    }
}
```

In the above code, `toString()` must return only the numeric value (i.e., “1” or “-1”).

**Figure 2**: A radial graph, with one point as the
central node. All other nodes are connected only
to this center by a directed edge. All edges have
the same length, and direct away from the center.

![Figure2](Figure2.png)

**Figure 3**: A geometric representation of an actual
radial graph. Each point is a Point object, all the
edges have the same length, and every edge direct
away from the center.
![Figure3](Figure3.png)

### Task2 ###

There is an interface called `Shape` provided in the `geometry` package. In this question, we will consider (20)
a `RadialGraph`, which is meant to represent objects of the type shown in Fig. **2** and Fig. **3**. Such graphs
show up all the time in social networks (e.g., the central node represents an influencer with the others
representing followers). Your task in this question is to complete the implementation of the `RadialGraph`
class, consistent with the requirements of the `Shap`e interface.
Most of the implementation is straight-forward. Implementing rotation (in the rotateBy(int degrees)
method), however, requires some mathematics!
Formally, rotation in the 2-dimensional Euclidean space is defined by a 2 × 2 matrix. To rotate all the
points in the x-y plane counterclockwise by an angle θ (in radians), with respect to the positive x axis
about the origin, a point (x, y) is transformed by



