(function(System) {
	'use strict';
	System.register([
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/ITestReporter',
		'eu.numberfour.mangelhaft.mangeltypes/n4/mangel/mangeltypes/TestSpy'
	], function($n4Export) {
		var ITestReporter, TestSpy, q, getLocation, updateURLParameter, getParm, filterClickHandler, HTMLReporter;
		getLocation = function getLocation() {
			return location.href;
		};
		updateURLParameter = function updateURLParameter(url, param, paramVal) {
			let urlObj = new URL(url), queryParts, rowsTxt;
			;
			queryParts = urlObj.search.split("&").filter((function(part) {
				return part.split('=')[0] != param;
			}).bind(this));
			queryParts.push(String(('' + param + '=' + encodeURIComponent(paramVal) + '')));
			urlObj.search = queryParts.join("&");
			return String(urlObj);
		};
		getParm = function getParm(name) {
			let names = new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)').exec(location.search);
			if (names) {
				return decodeURIComponent(names[1]);
			}
			return "";
		};
		filterClickHandler = function filterClickHandler(e, showOnly) {
			let testElm, groupElm, testStatus;
			;
			for(testElm of Array.from(document.querySelectorAll(".mangelhaft-test"))) {
				testStatus = testElm.getAttribute("data-mangelhaft-test-status");
				if (showOnly && showOnly.length && showOnly.indexOf(testStatus) === -1) {
					testElm.style.display = "none";
					testElm.classList.add("mangelhaft-hidden");
					testElm.classList.remove("mangelhaft-shown");
				} else {
					testElm.style.display = "block";
					testElm.classList.remove("mangelhaft-hidden");
					testElm.classList.add("mangelhaft-shown");
				}
			}
			for(groupElm of Array.from(document.querySelectorAll(".mangelhaft-group"))) {
				if (groupElm.querySelectorAll(".mangelhaft-test.mangelhaft-shown").length === 0) {
					groupElm.style.display = "none";
					groupElm.classList.add("mangelhaft-hidden");
				} else {
					groupElm.style.display = "block";
					groupElm.classList.remove("mangelhaft-hidden");
				}
			}
			window.history.pushState(null, null, updateURLParameter(getLocation(), "showOnly", showOnly.join(",")));
		};
		HTMLReporter = function HTMLReporter() {
			this.spy = undefined;
			this.reRunning = false;
			this.groupDivs = new Map();
			this.testDivs = new Map();
			this.statuses = new Map();
			this.showOnly = getParm("showOnly").split(",").map((function(showType) {
				return showType.trim();
			}).bind(this)).filter((function(showType) {
				return !!showType;
			}).bind(this));
			ITestReporter.$fieldInit(this, undefined, {
				spy: undefined,
				reRunning: undefined,
				groupDivs: undefined,
				testDivs: undefined,
				statuses: undefined,
				showOnly: undefined
			});
		};
		$n4Export('HTMLReporter', HTMLReporter);
		return {
			setters: [
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fITestReporter) {
					ITestReporter = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fITestReporter.ITestReporter;
				},
				function($_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestSpy) {
					TestSpy = $_import_eu_u002enumberfour_u002emangelhaft_u002emangeltypes_n4_u002fmangel_u002fmangeltypes_u002fTestSpy.TestSpy;
				}
			],
			execute: function() {
				q = document.querySelectorAll.bind(document);
				$makeClass(HTMLReporter, Object, [
					ITestReporter
				], {
					setFavIconRed: {
						value: function setFavIconRed___n4() {
							document.getElementById("page_favicon").setAttribute("href", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAH0lEQVQ4T2P8z8AAROQDxlEDGEbDgGE0DIBZaBikAwCl1B/x0/RuTAAAAABJRU5ErkJggg==");
						}
					},
					wrapFilterElement: {
						value: function wrapFilterElement___n4(elm, showOnly, classList, title) {
							let anchor = document.createElement("a");
							elm.classList.add(classList);
							anchor.setAttribute("href", updateURLParameter(getLocation(), "showOnly", showOnly.join(",")));
							anchor.setAttribute("title", title);
							anchor.appendChild(elm);
							anchor.addEventListener("click", function(e) {
								filterClickHandler.call(this, e, showOnly);
								e.preventDefault();
								e.stopPropagation();
								return false;
							}, true);
							return anchor;
						}
					},
					createFilterBox: {
						value: function createFilterBox___n4() {
							let fb = document.createElement("div"), left = document.createElement("div"), right = document.createElement("div"), skip = document.createElement("div"), fixme = document.createElement("div"), error = document.createElement("div"), all = document.createElement("div"), ok = document.createElement("div"), ran = document.createElement("div");
							;
							fb.classList.add("mangelhaft-filterbox");
							left.classList.add("mangelhaft-filterbox-left");
							right.classList.add("mangelhaft-filterbox-right");
							fb.appendChild(left);
							fb.appendChild(right);
							skip.textContent = "show skipped tests";
							fixme.textContent = "show fixme skipped tests";
							error.textContent = "show unsuccessful tests";
							ok.textContent = "show sucessful tests";
							all.textContent = "show all tests (remove filter)";
							ran.textContent = "show executed tests (non-skipped)";
							left.appendChild(this.wrapFilterElement(all, [], "mangelhaft-ALL", "Show all tests"));
							left.appendChild(this.wrapFilterElement(ok, [
								'PASSED'
							], "mangelhaft-OK", "Show only successful tests"));
							left.appendChild(this.wrapFilterElement(error, [
								'FAILED',
								'ERROR'
							], "mangelhaft-FAIL", "Show only unsuccessful tests"));
							right.appendChild(this.wrapFilterElement(ran, [
								'FAILED',
								'ERROR',
								'PASSED'
							], "mangelhaft-RAN", "Show only non-skipped results"));
							right.appendChild(this.wrapFilterElement(skip, [
								'SKIPPED',
								'SKIPPED_NOT_IMPLEMENTED',
								'SKIPPED_PRECONDITION',
								'SKIPPED_IGNORE',
								'SKIPPED_FIXME'
							], "mangelhaft-SKIPPED", "Show only skipped results"));
							right.appendChild(this.wrapFilterElement(fixme, [
								'SKIPPED_FIXME'
							], "mangelhaft-SKIPPED-FIXME", "Show only skipped results"));
							return fb;
						}
					},
					register: {
						value: function register___n4() {
							return $spawn(function*() {
								let that = this, testsElm = document.createElement("div"), header = document.createElement("div"), countersElm = document.createElement("div"), numGroupsElm = document.createElement("span"), numGroups = 0, numTestsElm = document.createElement("span"), numTests = 0, numTestsOkElm = document.createElement("span"), numTestsOk = 0, numTestsFailElm = document.createElement("span"), numTestsFail = 0, totalTestsElm = document.createElement("span"), numTestsRun = 0, numTestsSkipped = 0, numTestsSkippedElm = document.createElement("span");
								;
								testsElm.classList.add("mangelhaft");
								countersElm.classList.add("mangelhaft-counters");
								header.classList.add("mangelhaft-header");
								header.innerHTML = "<h1><a title='all tests' href='/test.html'>Mangelhaft</a></h1>";
								testsElm.appendChild(countersElm);
								testsElm.appendChild(that.createFilterBox());
								testsElm.appendChild(header);
								numGroupsElm.classList.add("mangelhaft-num-groups");
								countersElm.appendChild(numGroupsElm);
								countersElm.appendChild(this.wrapFilterElement(numTestsElm, [], "mangelhaft-num-tests", "Show all results"));
								countersElm.appendChild(this.wrapFilterElement(numTestsOkElm, [
									'PASSED'
								], "mangelhaft-num-OK", "Show only successful results"));
								countersElm.appendChild(this.wrapFilterElement(numTestsFailElm, [
									'FAILED',
									'ERROR'
								], "mangelhaft-num-FAIL", "Show only unsuccessful results"));
								countersElm.appendChild(this.wrapFilterElement(totalTestsElm, [
									'FAILED',
									'ERROR',
									'PASSED'
								], "mangelhaft-num-total-ran", "Show only non-skipped results"));
								countersElm.appendChild(this.wrapFilterElement(numTestsSkippedElm, [
									'SKIPPED',
									'SKIPPED_NOT_IMPLEMENTED',
									'SKIPPED_PRECONDITION',
									'SKIPPED_IGNORE',
									'SKIPPED_FIXME'
								], "mangelhaft-num-SKIPPED", "Show only skipped results"));
								var updateTotals = function updateTotals() {
									numGroupsElm.innerText = numGroups.toString();
									numTestsElm.innerText = numTests.toString();
									numTestsOkElm.innerText = numTestsOk.toString();
									numTestsFailElm.innerText = numTestsFail.toString();
									numTestsSkippedElm.innerText = numTestsSkipped.toString();
									totalTestsElm.innerText = numTestsRun.toString();
								};
								this.spy.testingStarted.add(function(numAllGroups, sessionId, numAllTests) {
									if (that.reRunning) {
										return;
									}
									document.body.appendChild(testsElm);
									numGroups = numAllGroups;
									numTests = numAllTests;
									updateTotals();
								});
								this.spy.groupStarted.add(function(group) {
									if (that.reRunning) {
										return;
									}
									let groupElm = document.createElement("div"), groupTitleElm = document.createElement("div"), moduleParts = group.fqn.split('.'), moduleAnchors, groupUrl = updateURLParameter(getLocation(), "filter", moduleParts.join(".")), filterStr = null;
									;
									groupUrl = updateURLParameter(groupUrl, "tests", "");
									groupElm.classList.add("mangelhaft-group");
									groupTitleElm.classList.add("mangelhaft-group-title");
									moduleAnchors = moduleParts.map((function(modulePart) {
										let url;
										filterStr = filterStr ? filterStr + "." + modulePart : modulePart;
										url = updateURLParameter(updateURLParameter(getLocation(), "filter", filterStr), "tests", "");
										return [
											"<a href='",
											url,
											"' >",
											modulePart,
											"</a>"
										].join("");
									}).bind(this));
									groupTitleElm.innerHTML = "<span class='mangelhaft-group-title-name'>" + moduleAnchors.join(".") + "</span>";
									groupElm.appendChild(groupTitleElm);
									that.groupDivs.set(group, groupElm);
									testsElm.appendChild(groupElm);
									group.tests.forEach(function(test) {
										let testName = test.description ? test.description : test.name, testElm = document.createElement("div"), status = document.createElement("span"), testNameElm = document.createElement("span"), tooltip = document.createElement("pre"), tooltipInner = document.createElement("code"), testUrl = updateURLParameter(groupUrl, "tests", test.name), nameParts = testName.match(/^(?:([a-zA-Z0-9]*)__)?(.*)___(.*)?$/);
										;
										if (nameParts) {
											if (nameParts[1]) {
												testName = "<span class='mangelhaft-test-name-prefix'>" + nameParts[1] + "</span>";
											} else {
												testName = "";
											}
											testName += nameParts[2].replace(/_/g, " ") + " <span class='mangelhaft-test-name-suffix'>" + nameParts[3] + "</span>";
										}
										if (!testName.match(/ /)) {
											testName = testName.replace(/^[a-z]|[A-Z]/g, function(letter, ii) {
												return ii === 0 ? letter.toUpperCase() : " " + letter.toLowerCase();
											});
										}
										tooltip.className = "mangelhaft-test-tooltip";
										tooltipInner.className = "prettyprint lang-js";
										tooltipInner.innerText = test.value.toString();
										tooltip.appendChild(tooltipInner);
										testElm.classList.add("mangelhaft-test");
										testNameElm.className = "mangelhaft-test-name";
										testNameElm.innerHTML = testName;
										testNameElm.appendChild(tooltip);
										testElm.appendChild(testNameElm);
										status.innerHTML = "<a title='re-run test' href='" + testUrl + "'>Running...</a>";
										testElm.appendChild(status);
										status.classList.add("mangelhaft-status");
										groupElm.appendChild(testElm);
										that.testDivs.set(testUrl, testElm);
										that.statuses.set(testUrl, status);
									});
								});
								this.spy.testFinished.add(function(group, test, testResult, rerunTestFunc) {
									let reason, stackElm = null, elapsed = document.createElement("span"), groupElm, testElm, status, module = group.fqn, groupUrl = updateURLParameter(getLocation(), "filter", module), testUrl = updateURLParameter(groupUrl, "tests", test.name), elapsedStr;
									;
									groupElm = that.groupDivs.get(group);
									testElm = that.testDivs.get(testUrl);
									status = that.statuses.get(testUrl);
									if (!that.reRunning) {
										numTestsRun += 1;
									}
									testElm.setAttribute("data-mangelhaft-test-status", testResult.testStatus);
									status.className = "";
									switch(testResult.testStatus) {
										case 'SKIPPED_FIXME':
											status.classList.add("mangelhaft-SKIPPED-FIXME");
										case 'SKIPPED_IGNORE':
										case 'SKIPPED_NOT_IMPLEMENTED':
										case 'SKIPPED_PRECONDITION':
										case 'SKIPPED':
											{
												status.classList.add("mangelhaft-SKIPPED");
												status.innerHTML = "<a title='re-run test' href='" + testUrl + "'>" + testResult.testStatus.toString() + "</a>";
												if (!that.reRunning) {
													numTestsSkipped += 1;
													numTestsRun -= 1;
												}
												break;
											}
										case 'PASSED':
											{
												status.classList.remove("mangelhaft-FAIL");
												status.classList.add("mangelhaft-OK");
												status.innerHTML = "<a title='re-run test' href='" + testUrl + "'>OK</a>";
												if (!that.reRunning) {
													numTestsOk += 1;
												}
												break;
											}
										case 'ERROR':
										case 'FAILED':
											{
												status.classList.add("mangelhaft-FAIL");
												status.innerHTML = "<a title='re-run test' name='fail' href='" + testUrl + "'>" + testResult.testStatus.toString() + "</a>";
												if (testResult.trace) {
													stackElm = document.createElement("pre");
													stackElm.innerText = testResult.trace.join("\n");
													stackElm.classList.add("mangelhaft-FAIL-stack");
													testElm.appendChild(stackElm);
												}
												if (!that.reRunning) {
													numTestsFail += 1;
												}
												that.setFavIconRed();
												break;
											}
									}
									if (testResult.message) {
										reason = document.createElement("span");
										reason.innerHTML = " [" + testResult.message + "]";
										reason.className = status.className + " " + "mangelhaft-reason";
										testElm.insertBefore(reason, stackElm);
									}
									if (that.showOnly && that.showOnly.length && that.showOnly.indexOf(testResult.testStatus.toString()) === -1) {
										(testElm).style.display = "none";
										testElm.classList.add("mangelhaft-hidden");
										testElm.classList.remove("mangelhaft-shown");
									} else {
										testElm.classList.remove("mangelhaft-hidden");
										testElm.classList.add("mangelhaft-shown");
										elapsedStr = testResult.elapsedTime ? testResult.elapsedTime.toString() : "< 1";
										elapsed.innerText = " (" + elapsedStr + "ms) ";
										elapsed.addEventListener("click", (function(e) {
											if (rerunTestFunc) {
												that.reRunning = true;
												status.innerHTML = "<a title='re-run test' href='" + testUrl + "'>Running...</a>";
												if (stackElm) {
													stackElm.innerHTML = "";
												}
												if (elapsed) {
													elapsed.innerHTML = "";
												}
												if (reason) {
													reason.innerHTML = "";
												}
												rerunTestFunc();
											}
										}).bind(this));
										testElm.insertBefore(elapsed, status);
										that.reRunning = false;
									}
									updateTotals();
								});
								this.spy.groupFinished.add((function(group, resultGroup) {
									if (that.reRunning) {
										return;
									}
									let groupElm = that.groupDivs.get(group);
									if (this.showOnly && this.showOnly.length && groupElm.querySelectorAll(".mangelhaft-test.mangelhaft-shown").length === 0) {
										(groupElm).style.display = "none";
										groupElm.classList.add("mangelhaft-hidden");
										groupElm.classList.remove("mangelhaft-shown");
									} else {
										groupElm.classList.remove("mangelhaft-hidden");
										groupElm.classList.add("mangelhaft-shown");
									}
								}).bind(this));
								this.spy.testingFinished.add(function(resultGroups) {
									if (that.reRunning) {
										return null;
									}
									let done = document.createElement("span"), groupElm;
									;
									done.innerText = "DONE";
									countersElm.appendChild(done);
									window.prettyPrint();
									return resultGroups;
								});
								(yield this);
								return;
							}.bind(this));
						}
					},
					spy: {
						value: undefined,
						writable: true
					},
					reRunning: {
						value: undefined,
						writable: true
					},
					groupDivs: {
						value: undefined,
						writable: true
					},
					testDivs: {
						value: undefined,
						writable: true
					},
					statuses: {
						value: undefined,
						writable: true
					},
					showOnly: {
						value: undefined,
						writable: true
					}
				}, {}, function(instanceProto, staticProto) {
					var metaClass = new N4Class({
						name: 'HTMLReporter',
						origin: 'eu.numberfour.mangelhaft.reporter.html',
						fqn: 'n4.mangel.reporter.html.HTMLReporter.HTMLReporter',
						n4superType: N4Object.n4type,
						allImplementedInterfaces: [
							'n4.mangel.mangeltypes.ITestReporter.ITestReporter'
						],
						ownedMembers: [
							new N4DataField({
								name: 'spy',
								isStatic: false,
								annotations: [
									new N4Annotation({
										name: 'Inject',
										details: []
									})
								]
							}),
							new N4Method({
								name: 'setFavIconRed',
								isStatic: false,
								jsFunction: instanceProto['setFavIconRed'],
								annotations: []
							}),
							new N4DataField({
								name: 'reRunning',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'groupDivs',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'testDivs',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'statuses',
								isStatic: false,
								annotations: []
							}),
							new N4DataField({
								name: 'showOnly',
								isStatic: false,
								annotations: []
							}),
							new N4Method({
								name: 'wrapFilterElement',
								isStatic: false,
								jsFunction: instanceProto['wrapFilterElement'],
								annotations: []
							}),
							new N4Method({
								name: 'createFilterBox',
								isStatic: false,
								jsFunction: instanceProto['createFilterBox'],
								annotations: []
							}),
							new N4Method({
								name: 'register',
								isStatic: false,
								jsFunction: instanceProto['register'],
								annotations: []
							})
						],
						consumedMembers: [],
						annotations: []
					});
					return metaClass;
				});
				Object.defineProperty(HTMLReporter, '$di', {
					value: {
						fieldsInjectedTypes: [
							{
								name: 'spy',
								type: TestSpy
							}
						]
					}
				});
			}
		};
	});
})(typeof module !== 'undefined' && module.exports ? require('n4js-node/index').System(require, module) : System);
//# sourceMappingURL=HTMLReporter.map
