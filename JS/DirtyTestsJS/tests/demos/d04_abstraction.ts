import 'jasmine';
const fs = require('fs')

let lyrics: string;

describe('abstraction', () => {
    it('noisy test', () => {
        lyrics = fs.readFileSync("BackInBlackLyrics.txt");
        expect(lyrics).toContain("I'm back in black");
    });

    it('readable test', () => {
        readLyrics("BackInBlackLyrics.txt");
        shouldContain("I'm back in black");
    });


    function readLyrics(path: string) {
        lyrics = fs.readFileSync("BackInBlackLyrics.txt");
    }

    function shouldContain(arg0: string) {
        expect(lyrics).toContain("I'm back in black");
    }
});

