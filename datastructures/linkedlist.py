class LinkedList():
    def __init__(self):
        self.size = 0
        self.head = None

    class Node():
        def __init__(self, val=None, next=None):
            self.val = val
            self.next = next

    def push(self, val):
        last = self.traverse(lambda *args: None)
        ins = self.Node(val)
        last.next = ins
        self.size += 1

    def insert(self, val, i):
        print("inserting ({}, {})".format(val, i))
        if not self.head or self.size == 0:
            self.head = self.Node(val)
            self.size += 1
        if i > 0 and i >= self.size:
            print("({}, {})".format(i, self.size))
            raise IndexError
        else:
            prev = self.find_node_prev(i)
            ins = self.Node(val, prev.next)
            prev.next = ins
        self.size += 1

    def find_node_prev(self, i):
        cur = self.head
        if i == 0:
            return cur
        pos = 1
        while cur.next and pos != i:
            pos += 1
            cur = cur.next
        return cur

    def traverse(self, f):
        cur = self.head
        f(cur.val)
        while cur.next:
            cur = cur.next
            f(cur.val)
        return cur

    def printlist(self):
        print("printing list")
        self.traverse(print)

    def remove(self, i):
        if not self.head or self.size == 0:
            raise TypeError
        cur = self.head
        if i == 0:
            return cur
        pos = 1
        while cur.next and pos != i:
            pos += 1
            cur = cur.next
        cur.next = cur.next.next

    def removeduplicates(self):
        pass


def main():
    L = LinkedList()
    L.insert(5, 0)
    L.push(6)
    L.push(7)
    L.printlist()
    print()

    L.insert(3, 1)
    L.printlist()
    print()

    L.insert(4, 2)
    L.printlist()
    print("size: ", L.size)
    print()

    # L.insert(10, 6)
    # L.printlist()
    # print()

    # L.insert(9, 6)
    # L.printlist()
    # print()

    L.remove(2)
    L.printlist()
    print()

if __name__ == "__main__":
    main()
