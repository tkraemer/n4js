var MObject = function MObject () {
        this.m = function() {
            console.log( "called m on MObject from ObjectExtension2.js")
        }
    }

module.exports = {
    "MObject": MObject
}