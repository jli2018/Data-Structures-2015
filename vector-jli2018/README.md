# vector-jli2018
vector-jli2018 created by Classroom for GitHub
A Vector is a data structure that is very similar to an Array, but its size is dynamic. Build a Vector class that contains the following variables and methods:

private Object [] data;
//How many items the vector can currently hold
private int capacity;
//How many items are in the vector
private int size;

//Default length 10
public Vector()

public Vector(int initCapacity)

//Copy constructor
public Vector(Vector<E> other)

//Add item to the first available space in the Vector (ie, the end)
public void add(E toAdd)

//Add item to the specified spot and shift the other items down
public void add(int index, E toAdd)

//Double the capacity of the vector
private void increaseCapacity()

//Return item at specified index
public E get(int index)*

//Remove and return item at specified index
//Shift the other items down
public E remove(int index)*

//Remove first instance of specified object
public boolean remove(E obj)

//Place object at specified location
public E set(int index, E obj)*

//Return the number of items in the vector
public int size()

//Remove all items from the vector
public void clear()

//Return whether or not the vector is empty
public boolean isEmpty()

//Return whether or not the vector contains specified object
public boolean contains(E obj)

//Return index of the first instance of specified object
public int indexOf(E obj)

//How the vector will be displayed once printed
public String toString()

//Create and return an iterator
public Iterator<E> iterator()

*For those methods that require a type cast from type Object to type E, you can use @SuppressWarnings("unchecked") before the method to ignore the unsafe operations warning.
