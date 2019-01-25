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

    def insert(self, val, i):
        if self.size == 0:
            self.head = self.Node(val)
        else:
            cur = self.traverse()
            ins = self.Node(val, cur.next)
            cur.next = ins

    def findpos(self, i):
        cur = self.head
        pos = 0
        while cur.next:
            if pos == i:
                return cur
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
    print("list size: {}".format(L.size))
    L.insert(5, 0)
    L.push(6)
    L.printlist()

if __name__ == "__main__":
    main()
