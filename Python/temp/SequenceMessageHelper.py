class SequenceMessageHelper:
    def __init__(self):
        self.version = ""
        self.resetOnError = False
        self.sequence = list()

    def addSequence(self, sequence):
        self.sequence.extend(sequence)
