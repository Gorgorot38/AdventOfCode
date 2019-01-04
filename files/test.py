#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Dec 13 21:34:41 2018

@author: Gaelmare
"""

#import os,sys
#import pprint
import numpy as np
#import string
#from collections import deque
#from operator import itemgetter
#from heapq import *
import re
import time

def main():
    xscans = []
    yscans = []
    scanlist = open("waterfall.txt").readlines()
    for scan in scanlist:
        if scan[0] == 'x':
            xscans.append(list(map(int, re.findall('-?\d+', scan))))
        else:
            yscans.append(list(map(int, re.findall('-?\d+', scan))))

    xarr=np.array(xscans)
    yarr=np.array(yscans)
    #establish size of grid, x=xmin-1..xmax+1, y= miny...maxy,
    xmin=min(xarr.min(0)[0],yarr.min(0)[1])-2 #subject of nasty bug that kept me up for two more hours... -1 wasn't enough space
    xmax=max(xarr.max(0)[0],yarr.max(0)[2])+1
    ymin=min(yarr.min(0)[0],xarr.min(0)[1])
    ymax=max(yarr.max(0)[0],xarr.max(0)[2])
    print(xmin,xmax,ymin,ymax)

    ground=np.full((xmax-xmin,ymax-ymin+2), -1, dtype=int)
    for s in xscans:
#        for y in range():
#            ground [s[0]-xmin] [y] = 0
         ground[s[0] - xmin,s[1] - ymin:s[2] + 1 - ymin] = 0
    for s in yscans:
#        for x in range(s[1]-xmin,s[2]+1-xmin):
#            ground [x] [s[0]-ymin] = 0
         ground[s[1] - xmin:s[2] +1 - xmin,s[0] - ymin] = 0

    spring=[500-xmin,ymin-ymin]
    activewater=[spring]
    #ground states -1:sand, 0:clay, 1:flowingwater, 2:stillwater
    freezewater=set()
    while True:
        newwater=[]
        for a in activewater:
            if a[1] == ymax-ymin+1:
                freezewater.add(tuple(a))
                continue
            dest=[a[0],a[1]+1]
            if ground[dest[0]][dest[1]] in [-1,1]:
                ground[a[0]][a[1]] = 1
                newwater.append(dest)
            elif ground[dest[0]][dest[1]] in (0,2):
                #flow left then right for walls
                arow=ground[:,a[1]]
                walls=np.argwhere((arow == 0) | (arow==2))
                lclay = [c[0] for c in walls if c <a[0]]
                if lclay:
                    lwall = max(lclay)
                else:  lwall = 0
                rclay = [c[0] for c in walls if c >a[0]]
                if rclay:
                    rwall = min(rclay)
                else: rwall=xmax-xmin+1

                floor=ground[lwall:rwall+1,a[1]+1]
                sand=np.argwhere((floor == -1) | (floor==1))
                if not sand.any(): #floor is still water or clay
                    ground[lwall+1:rwall,a[1]]=2
                    newwater.append([a[0],a[1]-1])
                    #newwater.append(spring)
                else:
                    lsand = [s[0] for s in sand if s < a[0] - lwall]
                    rsand = [s[0] for s in sand if s > a[0] - lwall]
                    if rsand:
                        rsand=min(rsand)+lwall
                        rwall = rsand + 1
                        newwater.append([rsand,a[1]+1])
                    if lsand:
                        lsand=max(lsand)+lwall
                        lwall = lsand - 1
                        newwater.append([lsand,a[1]+1])
                    ground[lwall+1:rwall,a[1]]=1
        #print(freezewater,activewater)
        if not newwater and freezewater:
            break
        activewater=[]#newwater

        for i in newwater:
            if i not in activewater:
                activewater.append(i)


    print(len(np.argwhere(ground >0)))
    print(len(np.argwhere(ground >1)))
    #np.savetxt("test",ground.T,delimiter='') #this along with   sed -e 's/.000000000000000000e+00//g' <test |sed -e 's/-1/-/g' gives text representation of solved grid
    #np.savetxt("test2",ground,delimiter='')
    #pprint.pprint(ground.T,width=300)

if __name__ == "__main__":
    # execute only if run as a script
    before=time.perf_counter()
    main()
    after=time.perf_counter()
    print(after-before)
