var MObject = function MObject () {
        this.m = function() {
            console.log( "called m on MObject from ObjectExtensionStandard.js")
        }
    }

module.exports = {
    "MObject": MObject
}