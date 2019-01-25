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
        print("inserting {} into {}".format(val, i))
        if not self.head or self.size == 0:
            self.head = self.Node(val)
        else:
            cur = self.findnode_prev(i)
            ins = self.Node(val, cur.next)
            cur.next = ins
        self.size += 1

    def findnode_prev(self, i):
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
        self.traverse(print)

    def remove(self, i):
        pass

    def removeduplicates(self):
        pass


def main():
    L = LinkedList()
    L.insert(5, 0)
    L.push(6)
    L.push(7)
    L.insert(3, 1)
    L.printlist()

if __name__ == "__main__":
    main()
