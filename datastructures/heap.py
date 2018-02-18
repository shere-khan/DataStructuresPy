class Heap:
    def __init__(self, order):
        self.__data = []
        self.__order = order

    def get_data(self):
        return self.__data

    def size(self):
        return len(self.__data)

    def __has_left(self, i):
        return self.__left(i) < self.size()

    def __has_right(self, i):
        return self.__right(i) < self.size()

    def __parent(self, j):
        return (j - 1) // 2

    def __left(self, j):
        return 2 * j + 1

    def __right(self, j):
        return 2 * j + 2

    def __swap(self, i, j):
        """
        :param i: The first index into the data array to be swapped
        :param j: The second index into the data array to be swapped

        """
        temp = self.__data[j]
        self.__data[j] = self.__data[i]
        self.__data[i] = temp

    def insert(self, n):
        """
        :param n: element to be inserted
        """
        self.__data.append(n)
        self.__heapify_up(self.size() - 1)

    def __heapify_up(self, i):
        """

        :param i: The index into the data array that needs to be corrected

        """
        parent = self.__parent(i)
        # If index in question is not root
        # and the projected swap satisfies the respective
        # min or max orientation of the heap

        if i > 0:
            if self.__order(self.__data[i], self.__data[parent]):
                self.__swap(i, parent)
                self.__heapify_up(parent)

    def dequeue(self):
        """
        Returns the first element of the queue. Internally, the queue must perform heapify down
        :return: first element of queue

        """
        if self.size() == 1:
            return self.__data.pop(0)

        # store element to return
        elem = self.__data[0]
        self.__swap(0, self.size() - 1)
        self.__data = self.__data[:-1]

        # lambda predicate < for a min heap
        self.__heapify_down(0)

        return elem

    def __heapify_down(self, i):
        if self.__has_left(i):
            left = self.__left(i)
            if self.__has_right(i):
                right = self.__right(i)
                j = left if self.__order(self.__data[left], self.__data[right]) else right
            else:
                j = left
            if self.__order(self.__data[j], self.__data[i]):
                self.__swap(i, j)
                self.__heapify_down(j)

    def __heapify(self, i, f):
        n = self.size()
        if n > 1:
            f(self.__data[i])
            # Otherwise i has a parent.
            # We try Heapify-Up
            self.__heapify_up(i)
            # Then we try Heapify-Down
            self.__heapify_down(i)

    def change_key(self, i, f):
        n = self.size()
        f(self.__data[i]) if n == 1 else self.__heapify(i, f)

    def heap_sort(self, l):
        self.__data = l
        for i in range((self.size() + 1)//2, 0):
            self.__heapify_up(i)
        r = []
        for i in range(self.__data):
            r.append(self.dequeue())

        return r

